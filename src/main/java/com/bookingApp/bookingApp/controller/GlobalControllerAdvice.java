package com.bookingApp.bookingApp.controller;

import com.bookingApp.bookingApp.error.ScreeningNotFoundException;
import com.bookingApp.bookingApp.error.SeatAlreadyReserved;
import com.bookingApp.bookingApp.error.SeatNotFoundException;
import com.bookingApp.bookingApp.error.TooLateToReserveException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ResponseBody
    @ExceptionHandler(ScreeningNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String screeningNotFoundHandler(ScreeningNotFoundException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SeatAlreadyReserved.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String seatAlreadyReservedHandler(SeatAlreadyReserved ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SeatNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String seatNotFoundHandler(SeatNotFoundException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TooLateToReserveException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String tooLateToReserveHandler(TooLateToReserveException ex){
        return ex.getMessage();
    }

}
