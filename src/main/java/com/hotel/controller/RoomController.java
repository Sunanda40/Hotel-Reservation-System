package com.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.model.Room;
import com.hotel.service.RoomService;

@RestController
@RequestMapping("/rooms")
@CrossOrigin("*")
public class RoomController {

    @Autowired
    private RoomService service;

    @GetMapping("/init")
    public String init() {
        service.initRooms();
        return "50 Rooms Created";
    }

    @GetMapping("/available")
    public List<Room> getAvailable() {
        return service.getAvailableRooms();
    }

    @GetMapping("/all")
    public List<Room> getAllRooms() {
        return service.getAllRooms();
    }
    @PutMapping("/vacate/{id}")
    public String vacateRoom(@PathVariable Long id) {

        Room room = service.getRoomById(id);

        if (room == null) return "Room not found";

        //  make room available
        room.setAvailable(true);
        service.update(room);

        //  remove customer (IMPORTANT)
        service.removeCustomerByRoom(room);

        return "Room vacated successfully";
    }
}