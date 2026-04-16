package com.hotelmanagementapplication.hotel_management.ControllerLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomTypeResponseDTO;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.RoomTypeRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.RoomTypeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/room-types")
public class RoomTypeController {

	  @Autowired
	    private RoomTypeService roomTypeService;

	    @PostMapping
	    public RoomTypeResponseDTO add(@Valid @RequestBody RoomTypeRequestDTO dto) {
	        return roomTypeService.addRoomType(dto);
	    }

	    @GetMapping("/{roomTypeId}")
	    public RoomTypeResponseDTO get(@PathVariable Long roomTypeId) {
	        return roomTypeService.getRoomTypeById(roomTypeId);
	    }

	    @GetMapping
	    public List<RoomTypeResponseDTO> getAll() {
	        return roomTypeService.getAllRoomTypes();
	    }

	    @PutMapping("/{roomTypeId}")
	    public RoomTypeResponseDTO update(@PathVariable Long roomTypeId,
	                                      @Valid @RequestBody RoomTypeRequestDTO dto) {
	        return roomTypeService.updateRoomType(roomTypeId, dto);
	    }

	    @DeleteMapping("/{roomTypeId}")
	    public String delete(@PathVariable Long roomTypeId) {
	        roomTypeService.deleteRoomType(roomTypeId);
	        return "RoomType deleted successfully";
	    }
}
