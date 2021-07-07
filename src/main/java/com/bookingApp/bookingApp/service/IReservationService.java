package com.bookingApp.bookingApp.service;

import com.bookingApp.bookingApp.DTOs.ScreeningInfoDto;
import com.bookingApp.bookingApp.DTOs.ScreeningsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface IReservationService {
    public List<ScreeningsDto> findAll(LocalDateTime dateFrom, LocalDateTime dateTo);
    public List<ScreeningsDto> findAll();
    public ScreeningInfoDto findById(Long id);
}
