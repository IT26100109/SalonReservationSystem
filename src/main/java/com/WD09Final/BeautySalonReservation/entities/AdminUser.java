package com.WD09Final.BeautySalonReservation.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class AdminUser extends User {

    private String department = "General Management";

    // Default constructor
    public AdminUser() {
        super();
    }

    // Parameterized constructor
    public AdminUser(String department) {
        super();
        this.department = department;
    }

    // Getters and Setters
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String getRoleName() {
        return "ADMIN";
    }

    @Override
    public String getDashboardRedirectUrl() {
        return "redirect:/admin/dashboard";
    }
}