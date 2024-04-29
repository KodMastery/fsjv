package mk.edu.codemaster.online.shop.repositories;

import mk.edu.codemaster.online.shop.entities.Order;
import mk.edu.codemaster.online.shop.entities.User;
import mk.edu.codemaster.online.shop.entities.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findOrderByUserAndStatus(User user, OrderStatus status);

}
