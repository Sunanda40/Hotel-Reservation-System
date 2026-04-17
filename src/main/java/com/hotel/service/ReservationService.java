package com.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.model.*;
import com.hotel.repo.*;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private CustomerRepo customerRepo;

    public void bookRoom(Long roomId, Customer customer) {

        System.out.println("Booking Room: " + roomId);
        System.out.println("Customer: " + customer.getName() + " " + customer.getPhone());

        Room room = roomRepo.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (!room.isAvailable()) {
            throw new RuntimeException("Room already booked");
        }

        //  Mark room as booked
        room.setAvailable(false);
        roomRepo.save(room);

        // Save customer
        customer.setRoom(room);
        Customer savedCustomer = customerRepo.save(customer);

        //  Save reservation
        Reservation reservation = new Reservation();
        reservation.setRoom(room);
        reservation.setCustomer(savedCustomer);

        reservationRepo.save(reservation);

        System.out.println("Booking DONE");
    }

    public void cancelReservation(Long id) {
        Reservation res = reservationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        Room room = res.getRoom();
        room.setAvailable(true);
        roomRepo.save(room);

        reservationRepo.delete(res);
    }
    public void removeCustomerByRoom(Room room) {

        List<Customer> customers = customerRepo.findAll();

        for (Customer c : customers) {
            if (c.getRoom() != null && c.getRoom().getId() == room.getId()) {
                customerRepo.delete(c);
            }
        }
    }
}