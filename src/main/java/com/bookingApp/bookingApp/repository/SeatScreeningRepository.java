package com.bookingApp.bookingApp.repository;

import com.bookingApp.bookingApp.model.SeatScreening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatScreeningRepository extends JpaRepository<SeatScreening,Long> {
}
