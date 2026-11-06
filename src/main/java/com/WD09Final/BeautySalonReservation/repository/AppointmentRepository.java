package com.WD09Final.BeautySalonReservation.repository;


import com.WD09Final.BeautySalonReservation.entities.Appointment;
import com.WD09Final.BeautySalonReservation.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    // Read: Customer view
    List<Appointment> findByCustomerOrderByAppointmentDateTimeDesc(Customer customer);

    // Read: Admin view
    List<Appointment> findAllByOrderByAppointmentDateTimeDesc();
}