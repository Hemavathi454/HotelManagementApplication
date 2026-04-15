package com.hotelmanagementapplication.hotel_management.ExceptionLayer;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(String message) {
        super(message);
    }
}