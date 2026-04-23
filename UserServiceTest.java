package com.hotelmanagementapplication.hotel_management.ServiceTestLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelmanagementapplication.hotel_management.DTOLayer.UserResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Users;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.UserRepository;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.UserRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceImplLayer.UserServiceImpl;
import com.hotelmanagementapplication.hotel_management.enumsLayer.Role;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    // ✅ CREATE USER
//    @Test
//    void shouldCreateUserSuccessfully() {
//
//        UserRequestDTO request = new UserRequestDTO();
//        request.setName("user");
//        request.setEmail("user@gmail.com");
//        request.setRole("CUSTOMER");
//
//        Users savedUser = new Users();
//        savedUser.setId(1L);
//        savedUser.setName("user");
//        savedUser.setEmail("user@gmail.com");
//        savedUser.setRole(Role.CUSTOMER);
//
//        when(userRepository.findByEmail("user@gmail.com"))
//                .thenReturn(Optional.empty());
//
//        when(userRepository.save(any(Users.class)))
//                .thenReturn(savedUser);
//
//        UserResponseDTO result = userService.createUser(request, "1234");
//
//        assertNotNull(result);
//        assertEquals("user", result.getName());
//    }
//
    // ❌ DUPLICATE EMAIL
    @Test
    void shouldThrowExceptionWhenEmailExists() {

        UserRequestDTO request = new UserRequestDTO();
        request.setEmail("user@gmail.com");

        when(userRepository.findByEmail("user@gmail.com"))
                .thenReturn(Optional.of(new Users()));

        assertThrows(RuntimeException.class, () -> {
            userService.createUser(request, "1234");
        });
    }

    // ✅ GET USER
    @Test
    void shouldReturnUserById() {

        Users user = new Users();
        user.setId(1L);
        user.setName("user");
        user.setEmail("user@gmail.com");
        user.setRole(Role.CUSTOMER);

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        UserResponseDTO result = userService.getUserById(1L);

        assertEquals("user", result.getName());
    }

    // ❌ USER NOT FOUND
    @Test
    void shouldThrowExceptionWhenUserNotFound() {

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            userService.getUserById(1L);
        });
    }
}