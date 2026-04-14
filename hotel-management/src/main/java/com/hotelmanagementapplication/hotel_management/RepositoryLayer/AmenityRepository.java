package com.hotelmanagementapplication.hotel_management.RepositoryLayer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelmanagementapplication.hotel_management.EntityClasses.Amenity;

public interface AmenityRepository extends JpaRepository<Amenity, Long>{

}
