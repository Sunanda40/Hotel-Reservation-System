package com.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.model.Customer;
import com.hotel.model.Reservation;
import com.hotel.model.Room;
import com.hotel.repo.CustomerRepo;
import com.hotel.repo.ReservationRepo;
import com.hotel.repo.RoomRepo;

@Service
public class RoomService {

    @Autowired
    private RoomRepo repo;
    @Autowired
    private CustomerRepo customerRepo;
    public void removeCustomerByRoom(Room room) {
        List<Customer> customers = customerRepo.findAll();

        for (Customer c : customers) {
            if (c.getRoom() != null && c.getRoom().getId() == room.getId()) {
                customerRepo.delete(c);
            }
        }
    }

    @Autowired
    private ReservationRepo reservationRepo;

    public void initRooms() {

        if (repo.count() > 0) return;

        for (int i = 1; i <= 50; i++) {

            Room room = new Room();

            if (i % 2 == 0) {
                room.setType("AC");
                room.setPrice(5000);
            } else {
                room.setType("Non-AC");
                room.setPrice(2500);
            }

            room.setAvailable(true);
            repo.save(room);
        }
    }

    public List<Room> getAllRooms() {
        return repo.findAll();
    }

    public List<Room> getAvailableRooms() {
        return repo.findByAvailableTrue();
    }

    public Room getRoomById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void update(Room room) {
        repo.save(room);
    }
    
    public void vacateRoom(Long roomId) {

        Room room = repo.findById(roomId).orElseThrow();

        // make room available
        room.setAvailable(true);
        repo.save(room);

        //  remove customer linked to this room
        List<Customer> customers = customerRepo.findAll();

        for (Customer c : customers) {
            if (c.getRoom() != null && c.getRoom().getId() == roomId) {
                customerRepo.delete(c);
            }
        }

        //  remove reservation also (FIXED)
        List<Reservation> reservations = reservationRepo.findAll();

        for (Reservation r : reservations) {
            if (r.getRoom() != null && r.getRoom().getId() == roomId) {
                reservationRepo.delete(r);
            }
        }
    }
}