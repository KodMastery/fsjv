package mk.edu.codemaster.online.shop.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mk.edu.codemaster.online.shop.entities.OrderItem;
import mk.edu.codemaster.online.shop.entities.dtos.OrderItemDTO;
import mk.edu.codemaster.online.shop.entities.dtos.ProductDTO;
import mk.edu.codemaster.online.shop.mappers.ProductMapper;
import mk.edu.codemaster.online.shop.services.BasketService;
import mk.edu.codemaster.online.shop.services.ProductService;
import mk.edu.codemaster.online.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/basket")
public class BasketController {

    @Autowired private BasketService basketService;
    @Autowired private UserService userService;


    @GetMapping("/add/{productId}")
    public String addToBasket(@PathVariable("productId") Long productId, HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session) {
        if(!userService.isLoggedIn(session)){
           return "redirect:/login";
        }
        List<OrderItemDTO> basket = (List<OrderItemDTO>) session.getAttribute("basket");
        if(basket == null){
            basket = new ArrayList<>();
        }
        basket = basketService.addToBasket(basket, productId);
        session.setAttribute("basket", basket);
        String referer = request.getHeader("Referer");
        redirectAttributes.addFlashAttribute("success", "Item added successfully!");
        return "redirect:" + (referer != null ? referer : "/home");
    }
    @GetMapping("/remove/{productId}")
    public String removeFromBasket(@PathVariable("productId") Long productId, HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session){
        if(!userService.isLoggedIn(session)){
            return "redirect:/login";
        }
        List<OrderItemDTO> basket = (List<OrderItemDTO>) session.getAttribute("basket");
        if(basket == null){
            basket = new ArrayList<>();
        }
        basket.removeIf(item -> item.getProductDTO().getId().equals(productId));
        session.setAttribute("basket", basket);
        String referer = request.getHeader("Referer");
        redirectAttributes.addFlashAttribute("success", "Item added successfully!");
        return "redirect:" + (referer != null ? referer : "/home");
    }

    @GetMapping("/cart")
    public String getBasket(Model model, HttpSession session){
        if(!userService.isLoggedIn(session)){
            return "redirect:/login";
        }
        List<OrderItemDTO> basket = (List<OrderItemDTO>) session.getAttribute("basket");
        if(basket == null){
            basket = new ArrayList<>();
            session.setAttribute("basket", basket);
        }
        model.addAttribute("total", basketService.getTotalPrice(basket));
        return "basket";
    }

}
