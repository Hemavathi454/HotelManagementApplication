package com.hotelmanagementapplication.hotel_management.ServiceTestLayer;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import com.hotelmanagementapplication.hotel_management.EntityClasses.Users;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.UserRepository;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.CustomUserDetailsService;
import com.hotelmanagementapplication.hotel_management.enumsLayer.Role;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {


	

	    @Mock
	    private UserRepository userRepository;

	    @InjectMocks
	    private CustomUserDetailsService customUserDetailsService;

	    @Test
	    void testLoadUserByUsernameSuccess() {
	        Users user = new Users();
	        user.setEmail("john@example.com");
	        user.setPassword("encodedPassword");
	        user.setRole(Role.CUSTOMER);

	        when(userRepository.findByEmail("john@example.com"))
	                .thenReturn(Optional.of(user));

	        UserDetails userDetails = customUserDetailsService.loadUserByUsername("john@example.com");

	        assertNotNull(userDetails);
	        assertEquals("john@example.com", userDetails.getUsername());
	        assertEquals("encodedPassword", userDetails.getPassword());
	        assertTrue(userDetails.getAuthorities().stream()
	                .anyMatch(auth -> auth.getAuthority().equals("ROLE_CUSTOMER")));
	    }

	    @Test
	    void testLoadUserByUsernameNotFound_Inverted() {

	        when(userRepository.findByEmail("missing@example.com"))
	                .thenReturn(Optional.empty());

	        // wrong expectation → should NOT throw
	        assertDoesNotThrow(() -> {
	            customUserDetailsService.loadUserByUsername("missing@example.com");
	        });
	    }
	}


