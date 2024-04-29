package mk.edu.codemaster.online.shop.services;

import mk.edu.codemaster.online.shop.entities.Order;
import mk.edu.codemaster.online.shop.entities.OrderItem;
import mk.edu.codemaster.online.shop.entities.Product;
import mk.edu.codemaster.online.shop.entities.User;
import mk.edu.codemaster.online.shop.entities.dtos.OrderItemDTO;
import mk.edu.codemaster.online.shop.entities.dtos.ProductDTO;
import mk.edu.codemaster.online.shop.entities.enums.OrderStatus;
import mk.edu.codemaster.online.shop.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class BasketService {

    @Autowired private OrderService orderService;
    @Autowired private ProductService productService;
    @Autowired private ProductMapper productMapper;


    public List<OrderItemDTO> addToBasket(List<OrderItemDTO> basket, Long productId){

        if(!basket.isEmpty()){
            if(basket.stream().map(OrderItemDTO::getProductDTO).map(ProductDTO::getId).toList().contains(productId)){
                for (OrderItemDTO orderItem : basket) {
                        if(orderItem.getProductDTO().getId().equals(productId)){
                            orderItem.setQuantity(orderItem.getQuantity() + 1);
                        }
                    }
            }else {
                basket.add(new OrderItemDTO(productMapper.productToProductDTO(productService.getProductById(productId))));
            }
        }else{
            basket.add(new OrderItemDTO(productMapper.productToProductDTO(productService.getProductById(productId))));
        }
        return basket;
    }

    public Double getTotalPrice(List<OrderItemDTO> basket) {
        AtomicReference<Double> totalPrice = new AtomicReference<>();
        totalPrice.set(0.0);
        basket.forEach(item -> totalPrice.set(totalPrice.get() + (item.getQuantity() * item.getProductDTO().getPrice())));
        return totalPrice.get();
    }
}
