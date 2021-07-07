package com.bookingApp.bookingApp.service;

import com.bookingApp.bookingApp.DTOs.*;
import com.bookingApp.bookingApp.error.ScreeningNotFoundException;
import com.bookingApp.bookingApp.error.SeatAlreadyReserved;
import com.bookingApp.bookingApp.error.SeatNotFoundException;
import com.bookingApp.bookingApp.error.TooLateToReserveException;
import com.bookingApp.bookingApp.model.Reservation;
import com.bookingApp.bookingApp.model.Screening;
import com.bookingApp.bookingApp.model.SeatScreening;
import com.bookingApp.bookingApp.model.Ticket;
import com.bookingApp.bookingApp.repository.ReservationRepository;
import com.bookingApp.bookingApp.repository.ScreeningRepository;
import com.bookingApp.bookingApp.repository.SeatScreeningRepository;
import com.bookingApp.bookingApp.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ReservationService implements IReservationService {
    private final ScreeningRepository screeningRepository;
    private final SeatScreeningRepository seatScreeningRepository;
    private final ReservationRepository reservationRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public ReservationService(ScreeningRepository screeningRepository, SeatScreeningRepository seatScreeningRepository,
                                ReservationRepository reservationRepository, TicketRepository ticketRepository){
        this.screeningRepository = screeningRepository;
        this.seatScreeningRepository = seatScreeningRepository;
        this.reservationRepository = reservationRepository;
        this.ticketRepository = ticketRepository;
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

        Collections.sort(screeningsDtos, new Comparator<ScreeningsDto>() {
            @Override
            public int compare(ScreeningsDto o1, ScreeningsDto o2) {
                if(o1.getTitle().compareTo(o2.getTitle())==0){
                    return o1.getDate().compareTo(o2.getDate());
                }else {
                    return o1.getTitle().compareTo(o2.getTitle());
                }
            }
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

        Collections.sort(screeningsDtos, new Comparator<ScreeningsDto>() {
            @Override
            public int compare(ScreeningsDto o1, ScreeningsDto o2) {
                if(o1.getTitle().compareTo(o2.getTitle())==0){
                    return o1.getDate().compareTo(o2.getDate());
                }else {
                    return o1.getTitle().compareTo(o2.getTitle());
                }
            }
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

    @Override
    @Transactional
    public ReservationBackDto reserve(ReservationDto reservation, Long screeningId){
        Screening screening = screeningRepository.findById(screeningId)
                .orElseThrow(()->new ScreeningNotFoundException(screeningId));
        double totalCost = 0;
        LocalDateTime expDate = null;

        Duration duration = Duration.between(LocalDateTime.now(),screening.getScreeningDate());
        if(duration.isNegative() || duration.getSeconds()*60 < 15){
            throw new TooLateToReserveException(screeningId);
        }

        Reservation r = new Reservation();
        r.setName(reservation.getName());
        r.setSurname(reservation.getSurname());

        for(ReservationSeatDto seat: reservation.getSeats()){
            SeatScreening s = screening.getSeatScreenings().stream()
                    .filter(seatScreening -> seatScreening.getSeat().getSeatNumber() ==seat.getSeatNumber())
                    .findAny()
                    .orElseThrow(()-> new SeatNotFoundException(seat.getSeatNumber()));
            if(s.isReserved()){
                throw new SeatAlreadyReserved(seat.getSeatNumber());
            }

            seatScreeningRepository.findById(s.getId())
                    .map(seatScreening -> {
                        seatScreening.setReserved(true);
                        return seatScreeningRepository.save(seatScreening);
                    });

            switch (seat.getTicketType()){
                case ADULT:
                    totalCost += 25;
                    break;
                case CHILD:
                    totalCost += 12.50;
                    break;
                case STUDENT:
                    totalCost += 18;
                    break;
            }

            Ticket ticket = new Ticket();
            ticket.setTicketType(seat.getTicketType());
            ticket.setSeatScreening(s);
            ticket.setReservation(r);
            ticketRepository.save(ticket);
            expDate = s.getScreening().getScreeningDate().minusMinutes(15);


        }
        r.setTotalCost(totalCost);
        r.setExpirationDate(expDate);
        reservationRepository.save(r);

        ReservationBackDto rbd = new ReservationBackDto();
        rbd.setExpirationDate(expDate);
        rbd.setTotalCost(totalCost);
        return rbd;
    }
}
