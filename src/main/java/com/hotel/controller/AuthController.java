package com.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hotel.model.User;
import com.hotel.repo.UserRepo;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    // LOGIN
    @PostMapping("/login")
    public User login(@RequestBody User user) {

        User dbUser = userRepo.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!dbUser.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return dbUser; // contains role
    }

    // REGISTER (optional for testing)
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userRepo.save(user);
    }
}