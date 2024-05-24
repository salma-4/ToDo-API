package com.app.userservice.controller;

import com.app.userservice.model.request.UserRequestDTO;
import com.app.userservice.model.response.UserResponseDTO;
import com.app.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PutMapping("/id/{id}/activated")
    public ResponseEntity<String> activateUser(@PathVariable Long id) {
        String message = userService.activateUser(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/id/{id}/deactivated")
    public ResponseEntity<String> deactivateUser(@PathVariable long id) {
        String message = userService.deactivateUser(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/id/{id}/user")
    public ResponseEntity<String> updateUser(@RequestBody UserRequestDTO updatedUser, @PathVariable long id) {
        String message = userService.updateUser(updatedUser, id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        String message = userService.deleteUser(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> listAllUsers() {
        List<UserResponseDTO> response = userService.getAllUser();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> findUserByEmail(@PathVariable String email) {
        UserResponseDTO response = userService.getUserByEmail(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/forgetPassword")
    public  ResponseEntity< String> forgetPassword(@RequestHeader("Authorization") String token) {
       String message= userService.forgetPassword(token);
       return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestHeader("Authorization") String headerToken, @RequestParam("otp") String otp, @RequestParam("newPassword") String newPassword) {
        String message = userService.changePassword(headerToken, otp, newPassword);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/regenerateOtp")
    public ResponseEntity<String> regenerateOtp(@RequestParam("email") String email) {
        String message = userService.regenerateOtp(email);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}

//TODO User Service Steps
//Generate a JWT token if the user called it with the correct username and password which be stored in registration step
//Generate OTP to active user account
//  Validating a token (extract data from JWT). Token validation typically involves checking the token's signature, expiration time, other claims and username existence.
// TODO determines whether the authenticated user has permission to access a particular resource or perform a specific action (Authorization)
// Password reset by generating an OTP and send it to the user email using java mail.
// handle users operations like (insert ,delete and update, search   ) users.
// TODO Document these apis using Swagger
// TODO Use best practices in APIs design while designing these APIs
// TODO LOGGING
//Add a global exception handlers to handle exceptions (Not_Found Exception for example)
//TODO Make unit testing for these APIs using JUnit5
//TODO Make a backend validation on the attributes
//TODO Make a Postman Collection with the above APIs
