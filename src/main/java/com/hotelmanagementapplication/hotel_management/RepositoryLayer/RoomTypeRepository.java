package com.hotelmanagementapplication.hotel_management.RepositoryLayer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelmanagementapplication.hotel_management.EntityClasses.RoomType;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long>{

}
