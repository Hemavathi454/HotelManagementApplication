package com.hotelmanagementapplication.hotel_management.EntityClasses;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Hotel {

    @Id
    @GeneratedValue
    (strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    public String location;

    public String description;

    @OneToMany(mappedBy = "hotel")
    public List<Room> rooms;

    @OneToMany(mappedBy = "hotel")
    public List<Review> reviews;

}
