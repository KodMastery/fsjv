package mk.edu.codemaster.online.shop.repositories;

import mk.edu.codemaster.online.shop.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}