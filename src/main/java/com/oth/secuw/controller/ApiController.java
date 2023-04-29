package com.oth.secuw.controller;

import com.oth.secuw.dto.SigninDto;
import com.oth.secuw.entities.User;
import com.oth.secuw.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/signin")
    public ResponseEntity<?> saveUser(@RequestBody SigninDto signinDto){
        try {
            User newUser = new User();
            newUser.setUsername(signinDto.getUsername());
            newUser.setEmail(signinDto.getEmail());
            newUser.setPassword(signinDto.getPassword());
            newUser.setRole(signinDto.getRole());
            userRepo.save(newUser);
            String message ="User "+newUser.getUsername()+" added";
            return ResponseEntity.ok(message);

        }catch (Exception ex){
            String message = "An error occurred while registering the User ";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }

    }
}
