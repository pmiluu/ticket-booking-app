package com.bookingApp.bookingApp.DTOs;

import com.bookingApp.bookingApp.model.TicketType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservationSeatDto {
    private int seatNumber;
    private TicketType ticketType;
}
