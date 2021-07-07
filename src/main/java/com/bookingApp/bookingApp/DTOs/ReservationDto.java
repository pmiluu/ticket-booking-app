package com.bookingApp.bookingApp.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
public class ReservationDto {
    @Pattern(regexp = "[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]{2,}",message = "Name should be at least three characters long and starts with a capital letter")
    private String name;
    @Pattern(regexp = "[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]{2,}",message = "Surname should be at least three characters long and starts with a capital letter")
    private String surname;
    private List<ReservationSeatDto> seats;
}
