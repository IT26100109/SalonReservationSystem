package com.WD09Final.BeautySalonReservation.repository;

import com.WD09Final.BeautySalonReservation.entities.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, Long> {
}
