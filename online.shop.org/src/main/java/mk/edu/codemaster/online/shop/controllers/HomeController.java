package mk.edu.codemaster.online.shop.controllers;

import jakarta.servlet.http.HttpSession;
import mk.edu.codemaster.online.shop.entities.dtos.UserDTO;
import mk.edu.codemaster.online.shop.mappers.ProductMapper;
import mk.edu.codemaster.online.shop.services.ProductService;
import mk.edu.codemaster.online.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired private UserService userService;
    @Autowired private ProductService productService;
    @Autowired private ProductMapper productMapper;

    @GetMapping
    public String getHome(Model model, HttpSession session) {
        if(!userService.isLoggedIn(session)){
            return "redirect:/login";
        }
        model.addAttribute("products", productMapper.convertToDTOList(productService.getAllProducts()));
        return "home";
    }

}
