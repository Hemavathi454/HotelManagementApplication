package com.hotelmanagementapplication.hotel_management.ExceptionLayer;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(String message) {
        super(message);
    }
}