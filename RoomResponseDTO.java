package com.hotelmanagementapplication.hotel_management.DTOLayer;

import java.util.List;

public class RoomResponseDTO {
	private Long id;
    private Integer roomNumber;
    private Boolean isAvailable;

    private Long hotelId;
    private Long roomTypeId;

    private List<Long> amenityIds;

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public List<Long> getAmenityIds() {
        return amenityIds;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public void setAmenityIds(List<Long> amenityIds) {
        this.amenityIds = amenityIds;
    }


}
