package com.WD09Final.BeautySalonReservation.contoller;


import com.WD09Final.BeautySalonReservation.dto.ChangePasswordForm;
import com.WD09Final.BeautySalonReservation.dto.ProfileUpdateForm;
import com.WD09Final.BeautySalonReservation.entities.Customer;
import com.WD09Final.BeautySalonReservation.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final UserService userService;

    @Autowired
    public CustomerController(UserService userService) {
        this.userService = userService;
    }

    // Display Customer Dashboard For specific customer
    @GetMapping("/dashboard")
    public String customerDashboard(Authentication auth, Model model) {
        Customer customer = (Customer) userService.findByEmail(auth.getName());
        model.addAttribute("customer", customer);
        return "customer/dashboard";
    }

    // Display Customer Dashboard For specific customer
    @GetMapping("/profile")
    public String viewProfile(Authentication auth, Model model) {
        Customer customer = (Customer) userService.findByEmail(auth.getName());

        model.addAttribute("customer", customer);
        model.addAttribute("activePage", "profile");

        //If there is a form data it doesn't create a new form otherwise it will
        if (!model.containsAttribute("profileForm")) {
            model.addAttribute("profileForm",
                    new ProfileUpdateForm(customer.getFullName(), customer.getEmail(), customer.getPhone()));
        }
        if (!model.containsAttribute("passwordForm")) {
            model.addAttribute("passwordForm", new ChangePasswordForm());
        }

        return "customer/profile";
    }

    // Process Profile update submissions
    @PostMapping("/profile/update")
    public String updateProfile(Authentication auth,
                                @Valid @ModelAttribute("profileForm") ProfileUpdateForm form,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("profileForm", form);
            redirectAttributes.addFlashAttribute("profileError",
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/customer/profile";
        }

        Customer customer = (Customer) userService.findByEmail(auth.getName());

        try {
            userService.updateUserProfile(customer.getId(), form.getFullName(), form.getEmail(), form.getPhone());
            redirectAttributes.addFlashAttribute("profileSuccess", "Your details have been updated.");
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("profileForm", form);
            redirectAttributes.addFlashAttribute("profileError", ex.getMessage());
        }

        return "redirect:/customer/profile";
    }

    // Process Password change submissions
    @PostMapping("/profile/password")
    public String changePassword(Authentication auth,
                                 @Valid @ModelAttribute("passwordForm") ChangePasswordForm form,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("passwordError",
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/customer/profile";
        }

        if (!form.getNewPassword().equals(form.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("passwordError", "New password and confirmation don't match.");
            return "redirect:/customer/profile";
        }

        Customer customer = (Customer) userService.findByEmail(auth.getName());

        try {
            userService.changePassword(customer.getId(), form.getCurrentPassword(), form.getNewPassword());
            redirectAttributes.addFlashAttribute("passwordSuccess", "Your password has been updated.");
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("passwordError", ex.getMessage());
        }

        return "redirect:/customer/profile";
    }
}