package com.hotel.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hotel.model.User;
import com.hotel.repo.UserRepo;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepo userRepo;

    @Override
    public void run(String... args) {

        if (userRepo.count() == 0) {

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setRole("ADMIN");

            User user = new User();
            user.setUsername("user");
            user.setPassword("user123");
            user.setRole("USER");

            userRepo.save(admin);
            userRepo.save(user);

            System.out.println("Default users created");
        }
    }
}