package com.bookingApp.bookingApp.service;

import com.bookingApp.bookingApp.DTOs.ScreeningInfoDto;
import com.bookingApp.bookingApp.DTOs.ScreeningsDto;
import com.bookingApp.bookingApp.DTOs.SeatDto;
import com.bookingApp.bookingApp.error.ScreeningNotFoundException;
import com.bookingApp.bookingApp.model.Screening;
import com.bookingApp.bookingApp.model.SeatScreening;
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
    public List<ScreeningsDto> findAll(){
        List<ScreeningsDto> screeningsDtos = new ArrayList<>();
        List<Screening> screenings = screeningRepository.findAll();
        screenings.forEach(screening -> {
            ScreeningsDto s = new ScreeningsDto();
            s.setScreeningId(screening.getId());
            s.setDate(screening.getScreeningDate());
            s.setTitle(screening.getMovie().getTitle());
            s.setRoom(screening.getSeatScreenings().get(0).getSeat().getRoom().getRoomNumber());

            screeningsDtos.add(s);
        });

        return screeningsDtos;
    }
    @Override
    public List<ScreeningsDto> findAll(LocalDateTime dateFrom, LocalDateTime dateTo) {
        List<ScreeningsDto> screeningsDtos = new ArrayList<>();
        List<Screening> screenings = screeningRepository.readScreeningsByScreeningDateBetween(dateFrom,dateTo);
        screenings.forEach(screening -> {
            ScreeningsDto s = new ScreeningsDto();
            s.setScreeningId(screening.getId());
            s.setDate(screening.getScreeningDate());
            s.setTitle(screening.getMovie().getTitle());
            s.setRoom(screening.getSeatScreenings().get(0).getSeat().getRoom().getRoomNumber());

            screeningsDtos.add(s);
        });

        return screeningsDtos;
    }

    @Override
    public ScreeningInfoDto findById(Long id) {
        ScreeningInfoDto s = new ScreeningInfoDto();
        Screening screening = screeningRepository.findById(id)
                .orElseThrow(()->new ScreeningNotFoundException(id));
        List<SeatDto> seatDtos = new ArrayList<>();

        s.setScreeningId(screening.getId());
        s.setDate(screening.getScreeningDate());
        s.setTitle(screening.getMovie().getTitle());
        s.setRoom(screening.getSeatScreenings().get(0).getSeat().getRoom().getRoomNumber());
        for (SeatScreening seatScreening: screening.getSeatScreenings()) {
            SeatDto seatDto = new SeatDto();
            seatDto.setSeatId(seatScreening.getSeat().getId());
            seatDto.setSeatNumber(seatScreening.getSeat().getSeatNumber());
            seatDto.setReserved(seatScreening.isReserved());

            seatDtos.add(seatDto);
        }
        s.setSeats(seatDtos);

        return s;
    }
}
