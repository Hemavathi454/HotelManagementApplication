package com.hotelmanagementapplication.hotel_management.MapperLayer;

import org.springframework.stereotype.Component;

import com.hotelmanagementapplication.hotel_management.DTOLayer.UserResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Users;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.UserRequestDTO;
import com.hotelmanagementapplication.hotel_management.enumsLayer.Role;

@Component
public class UserMapper {

	   public static UserResponseDTO toDTO(Users user) {

	        if (user == null) return null;

	        UserResponseDTO dto = new UserResponseDTO();
	        dto.setId(user.getId());
	        dto.setName(user.getName());
	        dto.setEmail(user.getEmail());
	        dto.setRole(user.getRole().name());

	        return dto;
	    }

	    public static Users toEntity(UserRequestDTO dto) {

	        if (dto == null) return null;

	        Users user = new Users();
	        user.setName(dto.getName());
	        user.setEmail(dto.getEmail());

	        if (dto.getRole() != null) {
	            user.setRole(Role.valueOf(dto.getRole().toUpperCase()));
	        } else {
	            user.setRole(Role.CUSTOMER);
	        }

	        return user;
	    }
}
