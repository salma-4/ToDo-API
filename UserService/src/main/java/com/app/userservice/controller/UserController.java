package com.app.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping( "/app/home")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello World");
    }
}

//TODO User Service
//Generate a JWT token if the user called it with the correct username and password which be stored in registration step
//Validating a token (extract data from JWT). Token validation typically involves checking the token's signature, expiration time, other claims and username existence.
//TODO determines whether the authenticated user has permission to access a particular resource or perform a specific action (Authorization)
//TODO Password reset by generating an OTP and send it to the user email using java mail.
//TODO Generate a JWT token if the user called it with the correct username and password which be stored in registration step
//TODO Validating a token (extract data from JWT). Token validation typically involves checking the token's signature, expiration time, other claims and username existence.
//TODO determines whether the authenticated user has permission to access a particular resource or perform a specific action (Authorization)
//TODO Password reset by generating an OTP and send it to the user email using java mail.
