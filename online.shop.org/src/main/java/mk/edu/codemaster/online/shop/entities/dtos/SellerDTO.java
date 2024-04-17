package mk.edu.codemaster.online.shop.entities.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SellerDTO {

    private Long id;

    private String sellerName;
    private String username;
    private String email;
    private String password;
    private int soldProducts = 0;
    private float rating;
    private List<ProductDTO> sellerProducts;
}
