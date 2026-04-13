package com.hotelmanagementapplication.hotel_management.EntityClasses;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    @Column(unique = true, nullable = false)
    public String email;

    public String password;

    public String role;

    @OneToMany(mappedBy = "user")
    public List<Reservation> reservations;

    @OneToMany(mappedBy = "user")
    public List<Review> reviews;
	

}
