package mk.edu.codemaster.online.shop.repositories;

import mk.edu.codemaster.online.shop.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findByUsername(String username);

    Optional<Object> findByEmail(String email);
}
