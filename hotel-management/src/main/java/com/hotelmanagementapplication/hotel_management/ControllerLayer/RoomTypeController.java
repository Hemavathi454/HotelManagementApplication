package com.hotelmanagementapplication.hotel_management.ControllerLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomTypeDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.RoomTypeService;
@RestController
@RequestMapping("/api/room-typess")
public class RoomTypeController {
	@Autowired
    private RoomTypeService roomTypeService;

    // CREATE
    @PostMapping
    public RoomTypeDTO add(@RequestBody RoomTypeDTO dto) {
        return roomTypeService.addRoomType(dto);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public RoomTypeDTO get(@PathVariable Long id) {
        return roomTypeService.getRoomTypeById(id);
    }

    // GET ALL
    @GetMapping
    public List<RoomTypeDTO> getAll() {
        return roomTypeService.getAllRoomTypes();
    }

    // UPDATE
    @PutMapping("/{id}")
    public RoomTypeDTO update(@PathVariable Long id,
                              @RequestBody RoomTypeDTO dto) {
        return roomTypeService.updateRoomType(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        roomTypeService.deleteRoomType(id);
        return "RoomType deleted successfully";
    }
	

}
