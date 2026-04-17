package com.hotel.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.model.Reservation;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {

}
