package mk.edu.codemaster.online.shop.controllers;

import mk.edu.codemaster.online.shop.entities.dtos.OrderDTO;
import mk.edu.codemaster.online.shop.entities.enums.OrderStatus;
import mk.edu.codemaster.online.shop.mappers.OrderMapper;
import mk.edu.codemaster.online.shop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired private OrderMapper orderMapper;

    @GetMapping("/orders")
    public String listOrders(Model model) {
        List<OrderDTO> orders = orderMapper.toDTOList(orderService.getAllOrders());
        model.addAttribute("orders", orders);
        model.addAttribute("orderDTO", new OrderDTO());
        return "orders";  // Name of the Thymeleaf template
    }

    @PostMapping("/orders")
    public String createOrder(@ModelAttribute OrderDTO order, RedirectAttributes redirectAttributes) {
        orderService.createOrder(orderMapper.toEntity(order));
        redirectAttributes.addFlashAttribute("success", "Order created successfully!");
        return "redirect:/orders";
    }

    @GetMapping("/removeOrder/{id}")
    public String removeOrder(@PathVariable("id") Long orderId, RedirectAttributes redirectAttributes) {
        orderService.deleteOrderById(orderId);
        redirectAttributes.addFlashAttribute("success", "Order removed successfully!");
        return "redirect:/orders";
    }


}
