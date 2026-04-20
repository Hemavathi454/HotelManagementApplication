package com.hotelmanagementapplication.hotel_management.RequesDTOLayer;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class RoomRequestDTO {
	@NotNull(message = "Room number is required")
    private Integer roomNumber;

    @NotNull(message = "Availability is required")
    private Boolean isAvailable;

    @NotNull(message = "Hotel ID is required")
    private Long hotelId;

    @NotNull(message = "Room Type ID is required")
    private Long roomTypeId;

    private List<Long> amenityIds;

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public Long getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public List<Long> getAmenityIds() {
		return amenityIds;
	}

	public void setAmenityIds(List<Long> amenityIds) {
		this.amenityIds = amenityIds;
	}

}
