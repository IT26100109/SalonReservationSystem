package com.WD09Final.BeautySalonReservation.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


// DTO in /customer/profile to carry data
public class ChangePasswordForm {

    @NotBlank(message = "Enter your current password")
    private String currentPassword;

    @NotBlank(message = "Enter a new password")
    @Size(min = 4, message = "New password must be at least 4 characters")
    private String newPassword;

    @NotBlank(message = "Confirm your new password")
    private String confirmPassword;

    //Default Constructor
    public ChangePasswordForm() {
    }

    // Getters and Setters
    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}