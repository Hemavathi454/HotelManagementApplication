package com.hotelmanagementapplication.hotel_management.ControllerTestLayer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.awt.PageAttributes.MediaType;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.hotelmanagementapplication.hotel_management.ControllerLayer.UserController;
import com.hotelmanagementapplication.hotel_management.DTOLayer.UserResponseDTO;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.UserRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.UserService;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    // ✅ CREATE - SUCCESS
    @Test
    void shouldCreateUserSuccessfully() {

        UserRequestDTO request = new UserRequestDTO();
        request.setName("Aarav");
        request.setEmail("aarav123@gmail.com");
        request.setRole("USER");

        UserResponseDTO response = new UserResponseDTO();
        response.setId(1L);
        response.setName("Aarav");
        response.setEmail("aarav123@gmail.com");
        response.setRole("USER");

        when(userService.createUser(any(), anyString()))
                .thenReturn(response);

        UserResponseDTO result = userController.createUser(request, "pass123");

        assertNotNull(result);
        assertEquals("Aarav", result.getName());
    }

    // ❌ CREATE - EMAIL ALREADY EXISTS
    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {

        UserRequestDTO request = new UserRequestDTO();
        request.setName("Meera");
        request.setEmail("meera@gmail.com");

        when(userService.createUser(any(), anyString()))
                .thenThrow(new RuntimeException("Email already exists"));

        assertThrows(RuntimeException.class, () -> {
            userController.createUser(request, "pass123");
        });
    }

    // ✅ GET USER - SUCCESS
    @Test
    void shouldGetUserById() {

        UserResponseDTO response = new UserResponseDTO();
        response.setId(2L);
        response.setName("Karthik");
        response.setEmail("karthik@mail.com");
        response.setRole("USER");

        when(userService.getUserById(2L))
                .thenReturn(response);

        UserResponseDTO result = userController.getUser(2L);

        assertEquals("Karthik", result.getName());
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound_Inverted() {

        when(userService.getUserById(99L))
                .thenThrow(new RuntimeException("User not found"));

        // wrong expectation
        assertDoesNotThrow(() -> {
            userController.getUser(99L);
        });
    }
    @Test
    void shouldReturnAllUsers_Inverted() {

        List<UserResponseDTO> list = List.of(
                createUser(1L, "Divya", "divya@mail.com"),
                createUser(2L, "Rohit", "rohit@mail.com")
        );

        when(userService.getAllUsers()).thenReturn(list);

        List<UserResponseDTO> result = userController.getAllUsers();

        // intentionally wrong
        assertNotEquals(2, result.size());
    }
    @Test
    void shouldReturnEmptyListWhenNoUsers_Inverted() {

        when(userService.getAllUsers()).thenReturn(Collections.emptyList());

        List<UserResponseDTO> result = userController.getAllUsers();

        // wrong expectation
        assertFalse(result.isEmpty());
    }
    // ✅ UPDATE - SUCCESS
    @Test
    void shouldUpdateUserSuccessfully() {

        UserRequestDTO request = new UserRequestDTO();
        request.setName("Nisha");
        request.setEmail("nisha@mail.com");

        UserResponseDTO response = new UserResponseDTO();
        response.setId(3L);
        response.setName("Nisha");

        when(userService.updateUser(eq(3L), any()))
                .thenReturn(response);

        UserResponseDTO result = userController.update(3L, request);

        assertEquals("Nisha", result.getName());
    }

    // ❌ UPDATE - USER NOT FOUND
    @Test
    void shouldThrowExceptionWhenUpdatingInvalidUser() {

        when(userService.updateUser(eq(50L), any()))
                .thenThrow(new RuntimeException("User not found"));

        assertThrows(RuntimeException.class, () -> {
            userController.update(50L, new UserRequestDTO());
        });
    }

    // ✅ DELETE - SUCCESS
    @Test
    void shouldDeleteUserSuccessfully() {

        doNothing().when(userService).deleteUser(4L);

        String result = userController.delete(4L);

        assertEquals("User deleted successfully", result);
    }

    // ❌ DELETE - USER NOT FOUND
    @Test
    void shouldThrowExceptionWhenDeletingInvalidUser() {

        doThrow(new RuntimeException("User not found"))
                .when(userService).deleteUser(70L);

        assertThrows(RuntimeException.class, () -> {
            userController.delete(70L);
        });
    }

    // 🔧 HELPER METHOD (clean code)
    private UserResponseDTO createUser(Long id, String name, String email) {

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setEmail(email);
        dto.setRole("USER");

        return dto;
    }
}