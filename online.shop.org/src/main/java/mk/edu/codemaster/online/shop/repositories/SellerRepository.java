package mk.edu.codemaster.online.shop.repositories;

import mk.edu.codemaster.online.shop.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
}
