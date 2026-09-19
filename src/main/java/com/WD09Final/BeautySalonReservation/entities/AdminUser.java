package com.WD09Final.BeautySalonReservation.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("ADMIN")
public class AdminUser extends User {

    private String department = "General Management";


    @Override
    public String getRoleName() {
        return "ADMIN";
    }

    @Override
    public String getDashboardRedirectUrl() {
        return "redirect:/admin/dashboard";
    }
}