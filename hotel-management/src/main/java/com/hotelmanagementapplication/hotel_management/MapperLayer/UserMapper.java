package com.hotelmanagementapplication.hotel_management.MapperLayer;

import org.springframework.stereotype.Component;

import com.hotelmanagementapplication.hotel_management.DTOLayer.UserDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Users;

@Component
public class UserMapper {

    // ENTITY → DTO
    public static UserDTO toDTO(Users user) {

        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());

        return dto;
    }

    // DTO → ENTITY
    public static Users toEntity(UserDTO dto) {

        if (dto == null) return null;

        Users user = new Users();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());

        // ❌ NEVER set password from DTO (security rule)
        return user;

}
}
