package com.WD09Final.BeautySalonReservation.service;


import com.WD09Final.BeautySalonReservation.dto.ChangePasswordForm;
import com.WD09Final.BeautySalonReservation.dto.ProfileUpdateForm;
import com.WD09Final.BeautySalonReservation.entities.Customer;
import jakarta.validation.Valid;

public interface CustomerProfileService {

    Customer getByEmail(String email);
    Customer getProfileByEmail(String email);
    Customer updateProfile(String currentEmail, ProfileUpdateForm form);

    // Overloaded update method if you ever need to pass a Customer entity directly
    Customer updateProfile(String email, Customer updatedCustomer);

    void changePassword(String currentEmail, @Valid ChangePasswordForm form);
}