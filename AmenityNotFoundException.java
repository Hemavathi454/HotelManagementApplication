package com.hotelmanagementapplication.hotel_management.ExceptionLayer;


	public class AmenityNotFoundException extends RuntimeException {
//	check if the class is the same when saving and loading objects.
	private static final long serialVersionUID = -5140703006775283914L;

		public AmenityNotFoundException(String message) {
	        super(message);
	    }
	}


