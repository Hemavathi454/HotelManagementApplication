package com.hotelmanagementapplication.hotel_management.RepositoryLayer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelmanagementapplication.hotel_management.HotelManagementApplication;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Payment;;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
	List<Payment> findByReservationId(Long reservationId);


}
