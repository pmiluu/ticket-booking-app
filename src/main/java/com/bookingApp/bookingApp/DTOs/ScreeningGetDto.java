package com.bookingApp.bookingApp.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ScreeningGetDto {
    private Long screeningId;
    private LocalDateTime date;
    private String title;
    private int room;
}
