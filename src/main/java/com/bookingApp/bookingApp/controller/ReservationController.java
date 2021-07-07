package com.bookingApp.bookingApp.controller;

import com.bookingApp.bookingApp.DTOs.ScreeningInfoDto;
import com.bookingApp.bookingApp.DTOs.ScreeningsDto;
import com.bookingApp.bookingApp.service.IReservationService;
import com.bookingApp.bookingApp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/screenings", produces = "application/json")
public class ReservationController {
    IReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService service){
        this.reservationService = service;
    }
    @GetMapping("/all")
    public List<ScreeningsDto> all(){
        return reservationService.findAll();
    }

    @GetMapping
    public List<ScreeningsDto> allByDate(@RequestParam("dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                       LocalDateTime dateFrom,
                                         @RequestParam("dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                        LocalDateTime dateTo){
        return reservationService.findAll(dateFrom,dateTo);
    }

    @GetMapping("/{id}")
    public ScreeningInfoDto one(@PathVariable Long id){
        return reservationService.findById(id);
    }
}
