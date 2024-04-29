package mk.edu.codemaster.online.shop.services;

import mk.edu.codemaster.online.shop.entities.Order;
import mk.edu.codemaster.online.shop.entities.OrderItem;
import mk.edu.codemaster.online.shop.entities.User;
import mk.edu.codemaster.online.shop.entities.dtos.OrderDTO;
import mk.edu.codemaster.online.shop.entities.dtos.OrderItemDTO;
import mk.edu.codemaster.online.shop.entities.enums.OrderStatus;
import mk.edu.codemaster.online.shop.repositories.OrderItemRepository;
import mk.edu.codemaster.online.shop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderItemRepository orderItemRepository;

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElseThrow(() -> new IllegalStateException("Order not found"));
    }

    public Order updateOrder(Long id, OrderDTO orderDTO){
        Order order = getOrderById(id);
        order.setStatus(orderDTO.getStatus());
        return orderRepository.save(order);
    }

    public Order createOrder(Order order){
        return orderRepository.save(order);
    }

    public void deleteOrderById(Long id){
        orderRepository.deleteById(id);
    }

    public OrderItem getOrderItemById(Long id){
        return orderItemRepository.findById(id).orElseThrow(() -> new IllegalStateException("OrderItem not found"));
    }

    public Order addItemInOrder(Long id, OrderItem newOrderItem){
        Order order = getOrderById(id);
        OrderItem orderItem = checkIfOrderItemExists(order, newOrderItem);
        if(orderItem == null){
            orderItem = orderItemRepository.save(newOrderItem);
            order.addItemToOrder(orderItem);
        }else{
            throw new IllegalStateException("Product for that order already exists");
//            order.removeItemFromOrder(orderItem);
//            orderItem.setQuantity(orderItem.getQuantity() + newOrderItem.getQuantity());
//            orderItem = orderItemRepository.save(orderItem);
//            order.addItemToOrder(orderItem);
        }
        setTotalPriceToOrder(order);
        return orderRepository.save(order);
    }

    private OrderItem checkIfOrderItemExists(Order order, OrderItem orderItem) {
        OrderItem item = null;
        for (OrderItem orItem : order.getOrderItems()) {
           if(orItem.getProduct().getId().equals(orderItem.getProduct().getId())){
               item = orItem;
               break;
           }
        }
        return item;
    }

    private void setTotalPriceToOrder(Order order) {
        AtomicReference<Double> total = new AtomicReference<>((double) 0);
        order.getOrderItems().forEach(item -> total.set(total.get() + (item.getProduct().getPrice() * item.getQuantity())));
        order.setTotalPrice(total.get());
    }

    public Order removeItemFromOrder(Long orderId, Long itemId){
        Order order = getOrderById(orderId);
        OrderItem item = getOrderItemById(itemId);
        order.removeItemFromOrder(item);
        setTotalPriceToOrder(order);
        orderItemRepository.delete(item);
        return orderRepository.save(order);
    }

    public void deleteOrderItemById(Long id){
        orderItemRepository.deleteById(id);
    }

    public OrderItem createOrderItem(OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }

    public OrderItem updateQuantityOrderItem(Long orderItemId, int quantity, String action){
        OrderItem orderItem = getOrderItemById(orderItemId);
        Order order = orderItem.getOrder();
        order.removeItemFromOrder(orderItem);
        switch (action){
            case "+" -> orderItem.setQuantity(orderItem.getQuantity() + quantity);
            case "-" -> orderItem.setQuantity(orderItem.getQuantity() - quantity);
            default -> throw new IllegalStateException("Unidentified action!");
        }
        orderItem = orderItemRepository.save(orderItem);
        order.addItemToOrder(orderItem);
        setTotalPriceToOrder(order);
        orderRepository.save(order);
        return orderItem;
    }

    public OrderItem updateOrderItem(Long id, OrderItemDTO orderItemDTO){
        OrderItem orderItem = getOrderItemById(id);
        orderItem.setQuantity(orderItemDTO.getQuantity());
        return orderItemRepository.save(orderItem);
    }

    public Order getOrderByUserAndStatus(User user, OrderStatus orderStatus){
        return orderRepository.findOrderByUserAndStatus(user, orderStatus).orElse(null);
    }

}
