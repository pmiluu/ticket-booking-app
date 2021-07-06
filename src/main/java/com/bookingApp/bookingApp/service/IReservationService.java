package com.bookingApp.bookingApp.service;

import com.bookingApp.bookingApp.DTOs.ScreeningGetDto;

import java.time.LocalDateTime;
import java.util.List;

public interface IReservationService {
    public List<ScreeningGetDto> findAll(LocalDateTime dateFrom, LocalDateTime dateTo);
    public List<ScreeningGetDto> findAll();
}
