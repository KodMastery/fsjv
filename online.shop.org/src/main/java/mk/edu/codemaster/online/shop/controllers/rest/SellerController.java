package mk.edu.codemaster.online.shop.controllers.rest;

import mk.edu.codemaster.online.shop.entities.dtos.SellerDTO;
import mk.edu.codemaster.online.shop.mappers.SellerMapper;
import mk.edu.codemaster.online.shop.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired private SellerService sellerService;
    @Autowired private SellerMapper sellerMapper;

    @GetMapping
    public List<SellerDTO> getAllSellers(){
        return sellerMapper.convertToDTOList(sellerService.getAllSellers());
    }

    @PostMapping
    public SellerDTO createSeller(@RequestBody SellerDTO sellerDTO){
        return sellerMapper.sellerToSellerDTO(sellerService.createSeller(sellerMapper.sellerDTOToSeller(sellerDTO)));
    }

}
