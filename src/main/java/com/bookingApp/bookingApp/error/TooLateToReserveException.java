package com.bookingApp.bookingApp.error;

public class TooLateToReserveException extends RuntimeException{
    public TooLateToReserveException(Long id){
        super("Could not reserve screening " + id + ". Seats can be booked at latest 15 minutes before the screening begins.");
    }
}
