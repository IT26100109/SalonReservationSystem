package com.WD09Final.BeautySalonReservation.contoller;


import com.WD09Final.BeautySalonReservation.entities.Customer;
import com.WD09Final.BeautySalonReservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Root Url Mapping to /login
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    // Gives Login View
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Gives Register View
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "register";
    }

    //Handles Customer Registration If Succeeds redirect to /login
    @PostMapping("/register")
    public String registerCustomer(@ModelAttribute("customer") Customer customer, Model model) {
        try {
            userService.registerCustomer(customer);
            return "redirect:/login?registered";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }
}