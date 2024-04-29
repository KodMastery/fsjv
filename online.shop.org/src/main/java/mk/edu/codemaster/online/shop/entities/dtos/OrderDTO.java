package mk.edu.codemaster.online.shop.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.edu.codemaster.online.shop.entities.OrderItem;
import mk.edu.codemaster.online.shop.entities.User;
import mk.edu.codemaster.online.shop.entities.enums.OrderStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;

    private Long userId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    private double totalPrice;

    private String shippingAddress;

    private OrderStatus status;

    private List<OrderItemDTO> orderItems = new ArrayList<>();

}
