package mk.edu.codemaster.online.shop.mappers;

import mk.edu.codemaster.online.shop.entities.Product;
import mk.edu.codemaster.online.shop.entities.dtos.ProductDTO;
import mk.edu.codemaster.online.shop.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    @Autowired private SellerService sellerService;


    public ProductDTO productToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        if (product.getSeller() != null) {
            productDTO.setSellerId(product.getSeller().getId());
        }
        return productDTO;
    }

    public Product productDTOToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setSeller(sellerService.getSellerById(productDTO.getSellerId()));
        return product;
    }

    public List<ProductDTO> convertToDTOList(List<Product> productList) {
        return productList.stream().map(this::productToProductDTO).toList();
    }

}
