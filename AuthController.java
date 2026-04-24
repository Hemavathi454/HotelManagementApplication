package com.hotelmanagementapplication.hotel_management.ControllerLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagementapplication.hotel_management.DTOLayer.UserResponseDTO;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.UserRequestDTO;
import com.hotelmanagementapplication.hotel_management.RequestClasses.LoginRequest;
import com.hotelmanagementapplication.hotel_management.RequestClasses.RegisterRequest;

import com.hotelmanagementapplication.hotel_management.ServiceLayer.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // REGISTER
    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody RegisterRequest request) {

        UserRequestDTO dto = new UserRequestDTO();
        dto.setName(request.getName());
        dto.setEmail(request.getEmail());

        return authService.register(dto, request.getPassword());
    }

    // LOGIN
    @PostMapping("/login")
    public UserResponseDTO login(@RequestBody LoginRequest request) {
        return authService.login(request.getEmail(), request.getPassword());
    }
}

