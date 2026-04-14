package com.hotelmanagementapplication.hotel_management.EntityClasses;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Amenity")
public class Amenity {


	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "amenity_id")
	    private Long id;

	    private String name;

	    private String description;
	    @ManyToMany(mappedBy = "amenities")
	    @JsonBackReference
	    private List<Hotel> hotels;

		public Long getId() {
			return id;
		}

		public List<Hotel> getHotels() {
			return hotels;
		}

		public void setHotels(List<Hotel> hotels) {
			this.hotels = hotels;
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

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	}

