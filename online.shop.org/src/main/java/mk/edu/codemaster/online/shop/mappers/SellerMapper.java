package mk.edu.codemaster.online.shop.mappers;

import mk.edu.codemaster.online.shop.entities.Seller;
import mk.edu.codemaster.online.shop.entities.dtos.SellerDTO;
import mk.edu.codemaster.online.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SellerMapper {

    @Autowired private ProductService productService;
    @Autowired private ProductMapper productMapper;

    public SellerDTO sellerToSellerDTO(Seller seller) {
        SellerDTO sellerDTO = new SellerDTO();
        sellerDTO.setId(seller.getId());
        sellerDTO.setSellerName(seller.getSellerName());
        sellerDTO.setUsername(seller.getUsername());
        sellerDTO.setEmail(seller.getEmail());
        sellerDTO.setPassword(seller.getPassword());
        sellerDTO.setSoldProducts(seller.getSoldProducts());
        sellerDTO.setRating(seller.getRating());
        sellerDTO.setSellerProducts(productMapper.convertToDTOList(productService.getProductsBySeller(seller)));
        return sellerDTO;
    }

    public Seller sellerDTOToSeller(SellerDTO sellerDTO) {
        Seller seller = new Seller();
        seller.setId(sellerDTO.getId());
        seller.setSellerName(sellerDTO.getSellerName());
        seller.setUsername(sellerDTO.getUsername());
        seller.setEmail(sellerDTO.getEmail());
        seller.setPassword(sellerDTO.getPassword());
        seller.setSoldProducts(sellerDTO.getSoldProducts());
        seller.setRating(sellerDTO.getRating());
        return seller;
    }

    public List<SellerDTO> convertToDTOList(List<Seller> sellerList){
        return sellerList.stream().map(this::sellerToSellerDTO).toList();
    }

}
