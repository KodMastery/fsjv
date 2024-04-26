package mk.edu.codemaster.online.shop.controllers.rest;

import mk.edu.codemaster.online.shop.entities.dtos.SellerDTO;
import mk.edu.codemaster.online.shop.mappers.SellerMapper;
import mk.edu.codemaster.online.shop.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerRestController {

    @Autowired private SellerService sellerService;
    @Autowired private SellerMapper sellerMapper;

    @GetMapping
    public ResponseEntity<List<SellerDTO>> getAllSellers(){
        List<SellerDTO> sellerDTOs = sellerMapper.convertToDTOList(sellerService.getAllSellers());
        return ResponseEntity.ok(sellerDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerDTO> getSeller(@PathVariable("id") Long id){
        SellerDTO sellerDTO = sellerMapper.sellerToSellerDTO(sellerService.getSellerById(id));
        return ResponseEntity.ok(sellerDTO);
    }

    @PostMapping
    public ResponseEntity<SellerDTO> createSeller(@RequestBody SellerDTO sellerDTO){
        SellerDTO createdSeller = sellerMapper.sellerToSellerDTO(sellerService.createSeller(sellerMapper.sellerDTOToSeller(sellerDTO)));
        return ResponseEntity.ok(createdSeller);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SellerDTO> updateSeller(@PathVariable("id") Long id, @RequestBody SellerDTO sellerDTO){
        SellerDTO updatedSeller = sellerMapper.sellerToSellerDTO(sellerService.updateSeller(id, sellerDTO));
        return ResponseEntity.ok(updatedSeller);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable("id") Long id){
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }

}
