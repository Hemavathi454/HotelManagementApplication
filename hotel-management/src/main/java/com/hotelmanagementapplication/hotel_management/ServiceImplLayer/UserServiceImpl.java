package com.hotelmanagementapplication.hotel_management.ServiceImplLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementapplication.hotel_management.DTOLayer.UserDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Users;
import com.hotelmanagementapplication.hotel_management.MapperLayer.UserMapper;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.UserRepository;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    // CREATE USER
    @Override
    public UserDTO createUser(UserDTO dto, String password) {

        if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        Users user = UserMapper.toEntity(dto);
        user.setPassword(password); // set separately (secure rule)

        Users saved = userRepository.save(user);

        return UserMapper.toDTO(saved);
    }

    // GET BY ID
    @Override
    public UserDTO getUserById(Long id) {

        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        return UserMapper.toDTO(user);
    }

    // GET ALL
    @Override
    public List<UserDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    // UPDATE
    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {

        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());

        Users updated = userRepository.save(user);

        return UserMapper.toDTO(updated);
    }

    // DELETE
    @Override
    public void deleteUser(Long id) {

        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        userRepository.delete(user);
    }

}
