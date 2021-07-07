package com.bookingApp.bookingApp.controller;

import com.bookingApp.bookingApp.error.ScreeningNotFoundException;
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
}
