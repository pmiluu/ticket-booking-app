package com.bookingApp.bookingApp.error;

public class SeatAlreadyReserved extends RuntimeException{
    public SeatAlreadyReserved(int id){
        super("Could not reserve seat number " + id + ". Seat is already reserved.");
    }
}
