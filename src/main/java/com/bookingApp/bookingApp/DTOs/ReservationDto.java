package com.bookingApp.bookingApp.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ReservationDto {
    private String name;
    private String surname;
    private List<ReservationSeatDto> seats;
}
