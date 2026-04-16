package com.hotelmanagementapplication.hotel_management.ServiceLayer;

import java.util.List;

import com.hotelmanagementapplication.hotel_management.DTOLayer.UserResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Users;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.UserRequestDTO;

public interface UserService {
	 UserResponseDTO createUser(UserRequestDTO dto, String password);

	    UserResponseDTO getUserById(Long id);

	    List<UserResponseDTO> getAllUsers();

	    UserResponseDTO updateUser(Long id, UserRequestDTO dto);

	    void deleteUser(Long id);

}
