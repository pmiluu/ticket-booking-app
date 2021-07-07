package com.bookingApp.bookingApp.error;

public class SeatNotFoundException extends RuntimeException{
    public SeatNotFoundException(int id){
        super("Could not find seat number " + id);
    }
}
