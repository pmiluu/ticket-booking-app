package com.bookingApp.bookingApp.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SeatDto {
    private Long seatId;
    private int seatNumber;
    private boolean reserved;
}
