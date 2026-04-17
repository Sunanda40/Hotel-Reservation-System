package com.hotel.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hotel.model.Customer;
import com.hotel.model.Room;
import com.hotel.repo.CustomerRepo;
import com.hotel.repo.RoomRepo;

import java.util.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private CustomerRepo customerRepo;

    //  ROOM + CUSTOMER TABLE DATA
    @GetMapping("/room-details")
    public List<Map<String, Object>> getRoomDetails() {

        List<Room> rooms = roomRepo.findAll();
        List<Customer> customers = customerRepo.findAll();

        List<Map<String, Object>> result = new ArrayList<>();

        for (Room room : rooms) {

            Map<String, Object> map = new HashMap<>();

            map.put("roomNo", room.getId());
            map.put("type", room.getType());
            map.put("price", room.getPrice());
            map.put("available", room.isAvailable());

            // default
            map.put("name", "-");
            map.put("phone", "-");

            for (Customer c : customers) {
                if (c.getRoom() != null && c.getRoom().getId() == room.getId()) {
                    map.put("name", c.getName());
                    map.put("phone", c.getPhone());
                }
            }

            result.add(map);
        }

        return result;
    }
}