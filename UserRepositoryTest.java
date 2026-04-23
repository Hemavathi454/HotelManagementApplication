package com.hotelmanagementapplication.hotel_management.RepositoryTestLayer;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import com.hotelmanagementapplication.hotel_management.EntityClasses.Users;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.UserRepository;
import com.hotelmanagementapplication.hotel_management.enumsLayer.Role;
@DataJpaTest 
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class UserRepositoryTest{

	 @Autowired
	    private UserRepository userRepository;
	 @AfterEach
	 void clean() {
	     userRepository.deleteAll();
	 }

	    @Test
	    void shouldSaveUserSuccessfully() {
	        Users user = new Users();
	        user.setName("Joy");
	        user.setEmail("joy3@gmail.com");
	        user.setPassword("123@joy5");
	        user.setRole(Role.CUSTOMER);

	        Users saved = userRepository.save(user);

	        assertNotNull(saved.getId());
	        assertEquals("Joy", saved.getName());
	    }

	    @Test
	    void shouldFindUserByEmail_Inverted() {

	        Users user = new Users();
	        user.setName("Joy");
	        user.setEmail("joy5@gmail.com");
	        user.setPassword("123@joy5");
	        user.setRole(Role.CUSTOMER);

	        userRepository.save(user);

	        Optional<Users> found = userRepository.findByEmail("joy5@gmail.com");

	        // wrong expectation
	        assertFalse(found.isPresent());
	    }
	    @Test
	    void shouldReturnEmptyForInvalidEmail() {
	        Optional<Users> found = userRepository.findByEmail("wrong@gmail.com");

	        assertFalse(found.isPresent());
	    }
	}


