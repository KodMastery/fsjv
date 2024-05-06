package mk.edu.codemaster.online.shop.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import mk.edu.codemaster.online.shop.entities.Seller;
import mk.edu.codemaster.online.shop.entities.User;
import mk.edu.codemaster.online.shop.repositories.SellerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class SellerServiceTest {

    @Mock
    private SellerRepository sellerRepository;

    @InjectMocks
    private SellerService sellerService;

    private Seller existingSeller;

    @BeforeEach
    void setUp() {
        existingSeller = Seller.builder()
                .id(1L)
                .username("oldUsername")
                .password("oldPassword")
                .email("john.doe@example.com")
                .sellerName("oldSellerName")
                .rating(3.5F).build();

        when(sellerRepository.findById(existingSeller.getId())).thenReturn(Optional.of(existingSeller));
        when(sellerRepository.save(any(Seller.class))).thenAnswer(i -> i.getArgument(0));
    }

    @Test
    void createSeller_createNewSeller_NoConflict(){

        Seller seller = Seller.builder()
                .id(2L)
                .username("newUsername")
                .password("newPassword")
                .email("jack.doe@example.com")
                .sellerName("newSellerName")
                .rating(2.3F).build();

        when(sellerRepository.findByUsername(seller.getUsername())).thenReturn(Optional.empty());
        when(sellerRepository.findByEmail(seller.getEmail())).thenReturn(Optional.empty());

        Seller newSeller = sellerService.createSeller(seller);

        assertNotNull(newSeller);
        assertEquals(seller.getUsername(), newSeller.getUsername());
        assertEquals(seller.getEmail(), newSeller.getEmail());

    }





}
