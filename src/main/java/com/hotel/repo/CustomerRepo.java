package com.hotel.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
	

}
