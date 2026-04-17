package com.hotel.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.model.Room;

public interface RoomRepo extends JpaRepository<Room, Long> {
	List<Room> findByAvailableTrue();

}
