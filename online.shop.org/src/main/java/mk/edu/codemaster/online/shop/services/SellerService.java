package mk.edu.codemaster.online.shop.services;

import mk.edu.codemaster.online.shop.entities.Seller;
import mk.edu.codemaster.online.shop.entities.dtos.SellerDTO;
import mk.edu.codemaster.online.shop.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    @Autowired private SellerRepository sellerRepository;


    public List<Seller> getAllSellers(){
        return sellerRepository.findAll();
    }

    public Seller createSeller(Seller seller){
        if(sellerRepository.findByUsername(seller.getUsername()).isPresent()){
            throw new IllegalStateException("Seller with that username already exists");
        } else if (sellerRepository.findByEmail(seller.getEmail()).isPresent()) {
            throw new IllegalStateException("Seller with that email already exists");
        }
        return sellerRepository.save(seller);
    }

    public Seller getSellerById(Long id){
        return sellerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Seller not found"));
    }

    public Seller updateSeller(Long id, SellerDTO sellerDTO) {
        Seller seller = getSellerById(id);
        if(sellerDTO.getPassword() != null && !seller.getPassword().equals(sellerDTO.getPassword())) {
            seller.setPassword(sellerDTO.getPassword());
        }
        if(seller.getRating() != sellerDTO.getRating()) {
            seller.setRating(sellerDTO.getRating());
        }
        if(seller.getSoldProducts() != sellerDTO.getSoldProducts()) {
            seller.setSoldProducts(sellerDTO.getSoldProducts());
        }
        return sellerRepository.save(seller);

    }

    public void deleteSeller(Long id) {
        sellerRepository.deleteById(id);
    }
}
