package mk.edu.codemaster.online.shop.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import mk.edu.codemaster.online.shop.entities.User;
import mk.edu.codemaster.online.shop.entities.dtos.UserDTO;
import mk.edu.codemaster.online.shop.mappers.UserMapper;
import mk.edu.codemaster.online.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model, HttpSession session) {
        if(userService.isLoggedIn(session)){
            return "redirect:/home";
        }
        model.addAttribute("user", new UserDTO());
        return "register"; // Thymeleaf template for registration
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "register";
        }

        // Check if user already exists
        if (userService.userExistsByUsername(userDTO.getUsername())) {
            result.rejectValue("username", "user.username", "An account already exists for this username.");
            return "register";
        }

        //add for email as well

        // Encrypt the password and create the user
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userService.createUser(userMapper.convertFromDTO(userDTO));

        redirectAttributes.addFlashAttribute("success", "Registration successful!");
        return "redirect:/login"; // Redirect to login page after successful registration
    }

    @GetMapping("/login")
    public String showLoginForm(HttpSession session, Model model) {
        if(userService.isLoggedIn(session)){
            return "redirect:/home";
        }
        model.addAttribute("user", new UserDTO());
        return "login"; // Thymeleaf template for login
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") UserDTO userDTO, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return "login";
        }

        if (!userService.userExistsByUsername(userDTO.getUsername())) {
            result.rejectValue("username", "user.username", "Invalid username");
            return "login";
        }
        User user = userService.getUserByUsername(userDTO.getUsername());
        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())){
            result.rejectValue("password", "user.password", "Invalid password");
            return "login";
        }
        session.setAttribute("user", user);
        return "redirect:/home"; // Redirect to a home page or dashboard after successful login
    }

}