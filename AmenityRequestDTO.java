package com.hotelmanagementapplication.hotel_management.RequesDTOLayer;
import jakarta.validation.constraints.NotBlank;

public class AmenityRequestDTO {
	

	    @NotBlank(message = "Amenity name is required")
	    private String name;

	    @NotBlank(message = "Description is required")
	    private String description;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}


}
