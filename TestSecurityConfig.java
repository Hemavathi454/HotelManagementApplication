package com.hotelmanagementapplication.hotel_management.CongifTestLayer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import org.springframework.security.crypto.password.PasswordEncoder;


import com.hotelmanagementapplication.hotel_management.SecurityLayer.SecurityConfig;

public class TestSecurityConfig {
	

	    private final SecurityConfig securityConfig = new SecurityConfig();

	    @Test
	    void testPasswordEncoderBean() {
	        PasswordEncoder encoder = securityConfig.passwordEncoder();
	        assertNotNull(encoder);
	        String rawPassword = "secret123";
	        String encoded = encoder.encode(rawPassword);

	        assertTrue(encoder.matches(rawPassword, encoded));
	    }

}
