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
        //TODO Add validation for username and email, and add passwordEncoder
        return sellerRepository.save(seller);
    }

    public Seller getSellerById(Long id){
        return sellerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Seller not found"));
    }

    public Seller updateSeller(Long id, SellerDTO sellerDTO) {
        //TODO Add validation and add passwordEncoder
        Seller seller = getSellerById(id);
        seller.setPassword(sellerDTO.getPassword());
        seller.setRating(sellerDTO.getRating());
        seller.setSoldProducts(sellerDTO.getSoldProducts());
        return sellerRepository.save(seller);

    }

    public void deleteSeller(Long id) {
        sellerRepository.deleteById(id);
    }
}
