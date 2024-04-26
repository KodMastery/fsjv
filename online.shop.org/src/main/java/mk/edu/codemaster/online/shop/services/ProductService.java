package mk.edu.codemaster.online.shop.services;

import mk.edu.codemaster.online.shop.entities.Product;
import mk.edu.codemaster.online.shop.entities.Seller;
import mk.edu.codemaster.online.shop.entities.dtos.ProductDTO;
import mk.edu.codemaster.online.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProductsBySeller(Seller seller) {
        return productRepository.findAllBySeller(seller);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalStateException("No such product"));
    }

    public Product updateProduct(Long id, ProductDTO productDTO) {
        Product product = getProductById(id);
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
