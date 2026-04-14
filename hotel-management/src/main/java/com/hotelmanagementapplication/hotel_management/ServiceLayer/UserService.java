package com.hotelmanagementapplication.hotel_management.ServiceLayer;

import java.util.List;

import com.hotelmanagementapplication.hotel_management.DTOLayer.UserDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Users;

public interface UserService {
	UserDTO createUser(UserDTO userDTO, String password);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);


}
