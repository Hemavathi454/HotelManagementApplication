package com.hotelmanagementapplication.hotel_management.DTOLayer;

public class RoomTypeResponseDTO {
	 private Long id;
	    private String typeName;
	    private String description;
	    private Integer maxOccupancy;
	    private Double pricePerNight;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
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
