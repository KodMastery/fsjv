package mk.edu.codemaster.online.shop.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.edu.codemaster.online.shop.entities.enums.OrderStatus;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "order_date")
    private Date orderDate;

    @Column(nullable = false, name = "total_price")
    private double totalPrice;

    @Column(nullable = false, name = "shipping_address")
    private String shippingAddress;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;
    
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

}
