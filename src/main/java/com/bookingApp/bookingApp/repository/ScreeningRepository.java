package com.bookingApp.bookingApp.repository;

import com.bookingApp.bookingApp.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening,Long> {
    List<Screening> readScreeningsByScreeningDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
