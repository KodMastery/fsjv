package mk.edu.codemaster.online.shop.repositories;

import mk.edu.codemaster.online.shop.entities.Product;
import mk.edu.codemaster.online.shop.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllBySeller(Seller seller);

}
