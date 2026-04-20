package com.hotelmanagementapplication.hotel_management.ConfigLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hotelmanagementapplication.hotel_management.EntityClasses.Users;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.UserRepository;
import com.hotelmanagementapplication.hotel_management.enumsLayer.Role;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (userRepository.findByEmail("admin2@gmail.com").isEmpty()) {

            Users admin = new Users();
            admin.setName("Admin");
            admin.setEmail("admin2@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);

            userRepository.save(admin);

            System.out.println("✅ Admin created");
        }
    }
}

