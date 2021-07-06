package com.bookingApp.bookingApp.service;

import com.bookingApp.bookingApp.DTOs.ScreeningGetDto;
import com.bookingApp.bookingApp.model.Screening;
import com.bookingApp.bookingApp.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService implements IReservationService {
    private final ScreeningRepository screeningRepository;

    @Autowired
    public ReservationService(ScreeningRepository screeningRepository){
        this.screeningRepository = screeningRepository;
    }
    public List<ScreeningGetDto> findAll(){
        List<ScreeningGetDto> screeningGetDtos = new ArrayList<>();
        List<Screening> screenings = screeningRepository.findAll();
        screenings.forEach(screening -> {
            ScreeningGetDto s = new ScreeningGetDto();
            s.setScreeningId(screening.getId());
            s.setDate(screening.getScreeningDate());
            s.setTitle(screening.getMovie().getTitle());
            s.setRoom(screening.getSeatScreenings().get(0).getSeat().getRoom().getRoomNumber());

            screeningGetDtos.add(s);
        });

        return screeningGetDtos;
    }
    @Override
    public List<ScreeningGetDto> findAll(LocalDateTime dateFrom, LocalDateTime dateTo) {
        List<ScreeningGetDto> screeningGetDtos = new ArrayList<>();
        List<Screening> screenings = screeningRepository.readScreeningsByScreeningDateBetween(dateFrom,dateTo);
        screenings.forEach(screening -> {
            ScreeningGetDto s = new ScreeningGetDto();
            s.setScreeningId(screening.getId());
            s.setDate(screening.getScreeningDate());
            s.setTitle(screening.getMovie().getTitle());
            s.setRoom(screening.getSeatScreenings().get(0).getSeat().getRoom().getRoomNumber());

            screeningGetDtos.add(s);
        });

        return screeningGetDtos;
    }
}
