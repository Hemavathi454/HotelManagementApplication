package com.hotelmanagementapplication.hotel_management.ControllerLayer;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hotelmanagementapplication.hotel_management.DTOLayer.UserDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.UserService;
@RestController
@RequestMapping("/api/userss")
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
