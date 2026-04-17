package com.hotelmanagementapplication.hotel_management.RequesDTOLayer;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class HotelRequestDTO {
	@NotBlank(message = "Hotel name is required")
    private String name;
	//GETTERS AND SETTERS

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

	@NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Description is required")
    private String description;

    private List<Long> amenityIds;

}
