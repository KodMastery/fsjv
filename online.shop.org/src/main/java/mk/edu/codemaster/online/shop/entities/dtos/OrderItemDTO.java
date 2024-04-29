package mk.edu.codemaster.online.shop.entities.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderItemDTO {

    private Long id;

    private Long orderId;

    private ProductDTO productDTO;

    private int quantity;

    public OrderItemDTO(ProductDTO productDTO){
        this.productDTO = productDTO;
        this.quantity = 1;
    }

}
