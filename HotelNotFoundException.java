package com.hotelmanagementapplication.hotel_management.ExceptionLayer;

public class HotelNotFoundException extends RuntimeException {
   
	private static final long serialVersionUID = -9066061597320327953L;

	public HotelNotFoundException(String message) {
        super(message);
    }
}