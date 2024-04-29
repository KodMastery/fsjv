package mk.edu.codemaster.online.shop.mappers;

import mk.edu.codemaster.online.shop.entities.Order;
import mk.edu.codemaster.online.shop.entities.dtos.OrderDTO;
import mk.edu.codemaster.online.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired private OrderItemMapper orderItemMapper;
    @Autowired private UserService userService;

    public OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());  // Assuming User has a suitable DTO or is used directly
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setShippingAddress(order.getShippingAddress());
        dto.setStatus(order.getStatus());
        dto.setOrderItems(order.getOrderItems().stream().map(orderItemMapper::toDTO).collect(Collectors.toList()));
        return dto;
    }

    public Order toEntity(OrderDTO dto) {
        if (dto == null) {
            return null;
        }
        Order order = new Order();
        order.setUser(userService.getUserById(dto.getUserId()));
        order.setOrderDate(dto.getOrderDate());
        order.setTotalPrice(dto.getTotalPrice());
        order.setShippingAddress(dto.getShippingAddress());
        order.setStatus(dto.getStatus());
        order.setOrderItems(dto.getOrderItems().stream().map(orderItemMapper::toEntity).collect(Collectors.toList()));
        return order;
    }

    public List<OrderDTO> toDTOList(List<Order> orders) {
        return orders.stream().map(this::toDTO).toList();
    }

}