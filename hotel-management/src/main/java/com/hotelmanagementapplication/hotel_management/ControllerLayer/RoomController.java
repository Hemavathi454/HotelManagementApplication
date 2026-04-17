package com.hotelmanagementapplication.hotel_management.ControllerLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.RoomService;

@RestController
@RequestMapping("/api/roomses")
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
