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
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Room;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.RoomService;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping
    public RoomDTO addRoom(@RequestBody RoomDTO dto) {
        return roomService.addRoom(dto);
    }

    @GetMapping("/{id}")
    public RoomDTO getRoom(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @GetMapping
    public List<RoomDTO> getAll() {
        return roomService.getAllRooms();
    }

    @GetMapping("/hotel/{hotelId}")
    public List<RoomDTO> getByHotel(@PathVariable Long hotelId) {
        return roomService.getRoomsByHotel(hotelId);
    }

    @PutMapping("/{id}")
    public RoomDTO update(@PathVariable Long id, @RequestBody RoomDTO dto) {
        return roomService.updateRoom(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return "Room deleted successfully";
    }

}
