package com.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.model.Customer;
import com.hotel.service.ReservationService;


@RestController
@RequestMapping("/reservations")
@CrossOrigin("*")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @PostMapping("/book/{roomId}")
    public String book(@PathVariable Long roomId,
                       @RequestBody Customer customer) {

        service.bookRoom(roomId, customer);
        return "Booked Successfully";
    }
    @GetMapping("/test")
    public String test() {
        System.out.println("TEST API CALLED");
        return "Working";
    }
    @DeleteMapping("/cancel/{id}")
    public String cancel(@PathVariable Long id) {
        service.cancelReservation(id);
        return "Room Vacated";
    }
}
