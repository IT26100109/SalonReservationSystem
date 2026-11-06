package com.WD09Final.BeautySalonReservation.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {

    @Column(name = "loyalty_points")
    private int loyaltyPoints = 0;

    @Column(name = "membership_tier")
    private String membershipTier = "SILVER";

    @Column(name = "total_spent")
    private double totalSpent = 0.0;

    // Default constructor
    public Customer() {
        super();
    }

    // Parameterized constructor
    public Customer(int loyaltyPoints, String membershipTier, double totalSpent) {
        super();
        this.loyaltyPoints = loyaltyPoints;
        this.membershipTier = membershipTier;
        this.totalSpent = totalSpent;
    }

    // Getters and Setters
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public String getMembershipTier() {
        return membershipTier;
    }

    public void setMembershipTier(String membershipTier) {
        this.membershipTier = membershipTier;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }

    @Override
    public String getRoleName() {
        return "CUSTOMER";
    }

    @Override
    public String getDashboardRedirectUrl() {
        return "redirect:/customer/dashboard";
    }
}