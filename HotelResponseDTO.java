package com.hotelmanagementapplication.hotel_management.DTOLayer;

import java.util.List;

public class HotelResponseDTO {

	private Long id;
    private String name;
    private String location;
    private String description;

    private List<Long> amenityIds;
    private List<Long> roomIds;
    
    //GETTERS AND SETTERS
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Long> getAmenityIds() {
		return amenityIds;
	}
	public void setAmenityIds(List<Long> amenityIds) {
		this.amenityIds = amenityIds;
	}
	public List<Long> getRoomIds() {
		return roomIds;
	}
	public void setRoomIds(List<Long> roomIds) {
		this.roomIds = roomIds;
	}
}
