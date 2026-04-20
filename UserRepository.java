package com.hotelmanagementapplication.hotel_management.RepositoryLayer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelmanagementapplication.hotel_management.EntityClasses.Users;

public interface UserRepository extends JpaRepository<Users, Long>{
	Optional<Users> findByEmail(String email);

}
