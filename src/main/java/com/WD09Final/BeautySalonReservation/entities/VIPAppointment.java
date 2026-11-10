package com.WD09Final.BeautySalonReservation.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("VIP")
public class VIPAppointment extends Appointment {

    private String dedicatedStylist;
    private static final double VIP_SURCHARGE = 1.15;

    public String getDedicatedStylist() {
        return dedicatedStylist;
    }

    public void setDedicatedStylist(String dedicatedStylist) {
        this.dedicatedStylist = dedicatedStylist;
    }

    @Override
    public Double calculateFinalPrice() {
        return getPrice() * VIP_SURCHARGE;
    }
}