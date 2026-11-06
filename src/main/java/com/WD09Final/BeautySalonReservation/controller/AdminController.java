package com.WD09Final.BeautySalonReservation.controller;

import com.WD09Final.BeautySalonReservation.entities.AdminUser;
import com.WD09Final.BeautySalonReservation.entities.Customer;
import com.WD09Final.BeautySalonReservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    // Single Dynamic View Dashboard Endpoint
    @GetMapping("/dashboard")
    public String adminDashboard(@RequestParam(name = "view", required = false) String view,
                                 Authentication auth,
                                 Model model) {

        AdminUser admin = (AdminUser) userService.findByEmail(auth.getName());
        List<Customer> customers = userService.getAllCustomers();

        String activeView = (view != null && !view.trim().isEmpty()) ? view : "customers";

        model.addAttribute("admin", admin);
        model.addAttribute("customers", customers != null ? customers : Collections.emptyList());
        model.addAttribute("totalCustomers", customers != null ? customers.size() : 0);
        model.addAttribute("currentView", activeView);



        return "admin/dashboard";
    }

    // Manual Admin Override: Update Customer Membership Tier
    @PostMapping("/customers/update-tier")
    public String updateCustomerTier(@RequestParam Long customerId,
                                     @RequestParam String newTier,
                                     RedirectAttributes redirectAttributes) {
        try {
            userService.updateCustomerTier(customerId, newTier);
            redirectAttributes.addFlashAttribute("successMessage", "Membership tier updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/dashboard?view=customers";
    }

    // Remove / Deactivate Customer Account
    @PostMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("successMessage", "Customer account removed successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/dashboard?view=customers";
    }


}