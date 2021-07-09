package com.bookingApp.bookingApp.DTOs;

import com.bookingApp.bookingApp.model.Seat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ScreeningInfoDto {
    private Long screeningId;
    private LocalDateTime date;
    private String title;
    private int room;
    private List<SeatDto> seats;
}
