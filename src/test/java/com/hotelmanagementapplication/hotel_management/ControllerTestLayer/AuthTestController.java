package com.hotelmanagementapplication.hotel_management.ControllerTestLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelmanagementapplication.hotel_management.ControllerLayer.AuthController;
import com.hotelmanagementapplication.hotel_management.DTOLayer.UserResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Users;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.UserRequestDTO;
import com.hotelmanagementapplication.hotel_management.RequestClasses.LoginRequest;
import com.hotelmanagementapplication.hotel_management.RequestClasses.RegisterRequest;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.AuthService;


	import static org.junit.jupiter.api.Assertions.*;
	import static org.mockito.Mockito.*;

	import org.junit.jupiter.api.Test;
	import org.junit.jupiter.api.extension.ExtendWith;
	import org.mockito.InjectMocks;
	import org.mockito.Mock;
	import org.mockito.junit.jupiter.MockitoExtension;

	@ExtendWith(MockitoExtension.class)
	public class AuthTestController {

	    @Mock
	    private AuthService authService;

	    @InjectMocks
	    private AuthController authController;

	    // ✅ Positive test for register
	    @Test
	    void testRegisterSuccess() {

	        RegisterRequest request = new RegisterRequest();
	        request.setName("John");
	        request.setEmail("john@example.com");
	        request.setPassword("password123");

	        UserResponseDTO responseDTO = new UserResponseDTO();
	        responseDTO.setName("John");
	        responseDTO.setEmail("john@example.com");
	        responseDTO.setRole("CUSTOMER");

	        when(authService.register(any(UserRequestDTO.class), eq("password123")))
	                .thenReturn(responseDTO);

	        UserResponseDTO result = authController.register(request);

	        assertNotNull(result);
	        assertEquals("John", result.getName());
	        assertEquals("john@example.com", result.getEmail());
	        assertEquals("CUSTOMER", result.getRole());
	    }

	    // ❌ Negative test for register (email already exists)
	    @Test
	    void testRegisterEmailAlreadyExists() {

	        RegisterRequest request = new RegisterRequest();
	        request.setName("Jane");
	        request.setEmail("jane@example.com");
	        request.setPassword("password123");

	        when(authService.register(any(UserRequestDTO.class), eq("password123")))
	                .thenThrow(new RuntimeException("Email already exists"));

	        RuntimeException ex = assertThrows(RuntimeException.class,
	                () -> authController.register(request));

	        assertEquals("Email already exists", ex.getMessage());
	    }

	    // ✅ Positive test for login (UPDATED)
	    @Test
	    void testLoginSuccess() {

	        LoginRequest request = new LoginRequest();
	        request.setEmail("john@example.com");
	        request.setPassword("password123");

	        UserResponseDTO responseDTO = new UserResponseDTO();
	        responseDTO.setEmail("john@example.com");
	        responseDTO.setName("John");
	        responseDTO.setRole("CUSTOMER");

	        when(authService.login("john@example.com", "password123"))
	                .thenReturn(responseDTO);

	        UserResponseDTO result = authController.login(request);

	        assertNotNull(result);
	        assertEquals("john@example.com", result.getEmail());
	        assertEquals("CUSTOMER", result.getRole());
	    }

	    // ❌ Negative test for login
	    @Test
	    void testLoginInvalidCredentials() {

	        LoginRequest request = new LoginRequest();
	        request.setEmail("wrong@example.com");
	        request.setPassword("badpass");

	        when(authService.login("wrong@example.com", "badpass"))
	                .thenThrow(new RuntimeException("Invalid email or password"));

	        RuntimeException ex = assertThrows(RuntimeException.class,
	                () -> authController.login(request));

	        assertEquals("Invalid email or password", ex.getMessage());
	    }
	}