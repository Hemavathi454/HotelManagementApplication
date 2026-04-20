package com.hotelmanagementapplication.hotel_management.ControllerLayer;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagementapplication.hotel_management.DTOLayer.UserDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Users;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
    private UserService userService;

    // CREATE USER (password passed separately)
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO dto,
                              @RequestParam String password) {
        return userService.createUser(dto, password);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // GET ALL
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // UPDATE
    @PutMapping("/{id}")
    public UserDTO update(@PathVariable Long id,
                          @RequestBody UserDTO dto) {
        return userService.updateUser(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }

}
