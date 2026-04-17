package com.hotelmanagementapplication.hotel_management.ServiceTestLayer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelmanagementapplication.hotel_management.EntityClasses.Users;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.UserRepository;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.AuthService;

@ExtendWith(MockitoExtension.class)
	public class AuthServiceTest {

	    @Mock
	    private UserRepository userRepository;

	    @InjectMocks
	    private AuthService authService;

	    
	   
	    @Test
	    void shouldThrowExceptionWhenUserNotFound_Inverted() {

	        when(userRepository.findByEmail("wrong@gmail.com"))
	                .thenReturn(Optional.empty());

	        // wrong expectation
	        assertDoesNotThrow(() -> {
	            authService.login("wrong@gmail.com", "1234");
	        });
	    }
	    // ❌ WRONG PASSWORD
	    @Test
	    void shouldThrowExceptionForInvalidPassword() {

	        Users user = new Users();
	        user.setEmail("test@gmail.com");
	        user.setPassword("correct");

	        when(userRepository.findByEmail("test@gmail.com"))
	                .thenReturn(Optional.of(user));

	        assertThrows(RuntimeException.class, () -> {
	            authService.login("test@gmail.com", "wrong");
	        });
	    }
	}


