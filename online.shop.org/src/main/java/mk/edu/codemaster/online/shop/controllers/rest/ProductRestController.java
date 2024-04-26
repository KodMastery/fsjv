package mk.edu.codemaster.online.shop.controllers.rest;

import mk.edu.codemaster.online.shop.entities.dtos.ProductDTO;
import mk.edu.codemaster.online.shop.mappers.ProductMapper;
import mk.edu.codemaster.online.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    @Autowired private ProductMapper productMapper;
    @Autowired private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getALllProducts(){
        List<ProductDTO> products = productMapper.convertToDTOList(productService.getAllProducts());
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id){
        ProductDTO productDTO = productMapper.productToProductDTO(productService.getProductById(id));
        return ResponseEntity.ok().body(productDTO);
    }
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO savedProduct = productMapper.productToProductDTO(productService.createProduct(productMapper.productDTOToProduct(productDTO)));
        return ResponseEntity.ok().body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable("id") Long id) {
        ProductDTO updatedProduct = productMapper.productToProductDTO(productService.updateProduct(id, productDTO));
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
