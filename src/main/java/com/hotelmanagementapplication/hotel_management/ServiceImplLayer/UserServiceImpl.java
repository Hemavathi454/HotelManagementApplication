package com.hotelmanagementapplication.hotel_management.ServiceImplLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotelmanagementapplication.hotel_management.DTOLayer.UserResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Users;
import com.hotelmanagementapplication.hotel_management.MapperLayer.UserMapper;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.UserRepository;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.UserRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.UserService;
import com.hotelmanagementapplication.hotel_management.enumsLayer.Role;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto, String password) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Users user = UserMapper.toEntity(dto);

        // ✅ FIXED
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.CUSTOMER);

        return UserMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {

        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        if (dto.getRole() != null) {
            user.setRole(Role.valueOf(dto.getRole().toUpperCase()));
        }

        return UserMapper.toDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {

        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
    }
}