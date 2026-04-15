package com.hotelmanagementapplication.hotel_management.ExceptionLayer;

public class HotelNotFoundException extends RuntimeException {
    public HotelNotFoundException(String message) {
        super(message);
    }
}
