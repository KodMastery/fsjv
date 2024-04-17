package mk.edu.codemaster.online.shop.controllers.rest;

import mk.edu.codemaster.online.shop.entities.dtos.ProductDTO;
import mk.edu.codemaster.online.shop.mappers.ProductMapper;
import mk.edu.codemaster.online.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired private ProductMapper productMapper;
    @Autowired private ProductService productService;

    @GetMapping
    public List<ProductDTO> getALllProducts(){
        return productMapper.convertToDTOList(productService.getAllProducts());
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        return productMapper.productToProductDTO(productService.createProduct(productMapper.productDTOToProduct(productDTO)));
    }
}
