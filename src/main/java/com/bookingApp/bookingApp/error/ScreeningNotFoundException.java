package com.bookingApp.bookingApp.error;

public class ScreeningNotFoundException extends RuntimeException{
    public ScreeningNotFoundException(Long id){
        super("Could not find screening " + id);
    }
}
