package com.hotelmanagementapplication.hotel_management.RequesDTOLayer;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RoomTypeRequestDTO {
	@NotBlank(message = "Type name is required")
    private String typeName;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Max occupancy is required")
    @Min(value = 1, message = "Max occupancy must be at least 1")
    private Integer maxOccupancy;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double pricePerNight;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMaxOccupancy() {
		return maxOccupancy;
	}

	public void setMaxOccupancy(Integer maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}

	public Double getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(Double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

   

}
