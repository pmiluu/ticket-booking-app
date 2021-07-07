package com.bookingApp.bookingApp.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReservationBackDto {
    private double totalCost;
    private LocalDateTime expirationDate;
}
