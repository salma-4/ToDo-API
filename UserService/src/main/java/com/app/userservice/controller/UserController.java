package com.app.userservice.controller;

import com.app.userservice.model.request.UserRequestDTO;
import com.app.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/id/")
public class UserController {
    private final UserService userService;
  @PutMapping("{id}/activated")
    public ResponseEntity<String > activateUser(@PathVariable Long id){
      String message = userService.activateUser(id);
      return new ResponseEntity<>(message, HttpStatus.OK);
  }
    @PutMapping("{id}/deactivated")
    public ResponseEntity<String> deactivateUser(@PathVariable long id){
        String message = userService.deactivateUser(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping("/{id}/user")
    public ResponseEntity<String > updateUser(@RequestBody UserRequestDTO updatedUser,@PathVariable long id){
      String message = userService.updateUser(updatedUser,id);
      return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String > deleteUser(@PathVariable long id){
      String message = userService.deleteUser(id);
      return new ResponseEntity<>(message,HttpStatus.OK);
    }

}

//TODO User Service Steps
//Generate a JWT token if the user called it with the correct username and password which be stored in registration step
//Generate OTP to active user account
//  Validating a token (extract data from JWT). Token validation typically involves checking the token's signature, expiration time, other claims and username existence.
// TODO determines whether the authenticated user has permission to access a particular resource or perform a specific action (Authorization)
// TODO Password reset by generating an OTP and send it to the user email using java mail.
// handle users operations like (insert ,delete and update, //TODO search   ) users.
// TODO Document these apis using Swagger
// TODO Use best practices in APIs design while designing these APIs
// TODO LOGGING
//Add a global exception handlers to handle exceptions (Not_Found Exception for example)
//TODO Make unit testing for these APIs using JUnit5
//TODO Make a backend validation on the attributes
//TODO Make a Postman Collection with the above APIs
