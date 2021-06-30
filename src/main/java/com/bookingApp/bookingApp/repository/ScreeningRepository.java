package com.bookingApp.bookingApp.repository;

import com.bookingApp.bookingApp.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningRepository extends JpaRepository<Screening,Long> {
}
