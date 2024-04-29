package mk.edu.codemaster.online.shop.mappers;

import mk.edu.codemaster.online.shop.entities.OrderItem;
import mk.edu.codemaster.online.shop.entities.dtos.OrderItemDTO;
import mk.edu.codemaster.online.shop.services.OrderService;
import mk.edu.codemaster.online.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderItemMapper {

    @Autowired private ProductService productService;
    @Autowired private OrderService orderService;
    @Autowired private ProductMapper productMapper;

    public OrderItemDTO toDTO(OrderItem orderItem) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(orderItem.getId());
        dto.setOrderId(orderItem.getOrder().getId());
        dto.setProductDTO(productMapper.productToProductDTO(orderItem.getProduct()));
        dto.setQuantity(orderItem.getQuantity());
        return dto;
    }

    public OrderItem toEntity(OrderItemDTO dto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setOrder(orderService.getOrderById(dto.getOrderId()));
        orderItem.setProduct(productService.getProductById(dto.getProductDTO().getId()));
        return orderItem;
    }

    public List<OrderItemDTO> toDTOList(List<OrderItem> orderItems) {
        return orderItems.stream().map(this::toDTO).toList();
    }

}