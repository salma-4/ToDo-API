package com.app.userservice.controller;

import com.app.userservice.model.request.UserRequestDTO;
import com.app.userservice.model.response.UserResponseDTO;
import com.app.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Activate user via id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, User activated enabled = true ")
            ,@ApiResponse(responseCode = "404",description = "NOT_FOUND, No user with this id")
            ,@ApiResponse(responseCode = "409",description = "CONFLICT, User already active")
            ,@ApiResponse(responseCode = "403",description = "FORBIDDEN, CHECK YOUR TOKEN")
    })
    @PutMapping("/id/{id}/activated")
    public ResponseEntity<String> activateUser(@PathVariable Long id) {
        String message = userService.activateUser(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "Deactivate user via id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, User deactivated enabled = true ")
            ,@ApiResponse(responseCode = "404",description = "NOT_FOUND, No user with this id")
            ,@ApiResponse(responseCode = "409",description = "CONFLICT, User already deactive")
            ,@ApiResponse(responseCode = "403",description = "FORBIDDEN, CHECK YOUR TOKEN")
    })
    @PutMapping("/id/{id}/deactivated")
    public ResponseEntity<String> deactivateUser(@PathVariable long id) {
        String message = userService.deactivateUser(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "Update user via id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, User updated  ")
            ,@ApiResponse(responseCode = "404",description = "NOT_FOUND, No user with this id")
            ,@ApiResponse(responseCode = "403",description = "FORBIDDEN, CHECK YOUR TOKEN")
            ,@ApiResponse(responseCode = "409",description = "CONFLICT,  Updated email is  already exist")

    })
    @PutMapping("/id/{id}/user")
    public ResponseEntity<String> updateUser(@RequestBody UserRequestDTO updatedUser, @PathVariable long id) {
        String message = userService.updateUser(updatedUser, id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "Delete user via id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, User activated enabled = true ")
            ,@ApiResponse(responseCode = "403",description = "FORBIDDEN, CHECK YOUR TOKEN")
            ,@ApiResponse(responseCode = "404",description = "NOT_FOUND, No user with this id")
    })
    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        String message = userService.deleteUser(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @Operation(summary = "List all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK,All ")
            ,@ApiResponse(responseCode = "403",description = "FORBIDDEN, CHECK YOUR TOKEN")
            ,@ApiResponse(responseCode = "404",description = "NOT_FOUND, No users found")
    })
    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> listAllUsers() {
        List<UserResponseDTO> response = userService.getAllUser();
        System.out.println(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @Operation(summary = "Find user by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK,User found ")
            ,@ApiResponse(responseCode = "403",description = "FORBIDDEN, CHECK YOUR TOKEN")
            ,@ApiResponse(responseCode = "404",description = "NOT_FOUND, No user found with this email")
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> findUserByEmail(@PathVariable String email) {
        UserResponseDTO response = userService.getUserByEmail(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @Operation(summary = "Forget password send OTP ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK,Look at your email ")
            ,@ApiResponse(responseCode = "404",description = "NOT_FOUND, No users found with this email in token")
            ,@ApiResponse(responseCode = "403",description = "FORBIDDEN, CHECK YOUR TOKEN")

    })
    @PostMapping("/forgetPassword")
    public  ResponseEntity< String> forgetPassword(@RequestHeader("Authorization") String token) {
       String message= userService.forgetPassword(token);
       return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @Operation(summary = "Change password via OTP send ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK,Password changed ")
            ,@ApiResponse(responseCode = "400",description = "BAD_REQUEST, CHECK YOUR OTP")
            ,@ApiResponse(responseCode = "403",description = "FORBIDDEN, CHECK YOUR TOKEN")
            ,@ApiResponse(responseCode = "404",description = "NOT_FOUND, No users found with this email in token")
    })
    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestHeader("Authorization") String headerToken, @RequestParam("otp") String otp, @RequestParam("newPassword") String newPassword) {
        String message = userService.changePassword(headerToken, otp, newPassword);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @Operation(summary = "Regenerate OTP with email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK,Look at your email ")
            ,@ApiResponse(responseCode = "404",description = "NOT_FOUND, No users found with this email in token")
            ,@ApiResponse(responseCode = "403",description = "FORBIDDEN, CHECK YOUR TOKEN")

    })
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
// Document these apis using Swagger
//  Use best practices in APIs design while designing these APIs
// TODO LOGGING
//Add a global exception handlers to handle exceptions (Not_Found Exception for example)
//TODO Make unit testing for these APIs using JUnit5
//TODO Make a backend validation on the attributes
//Make a Postman Collection with the above APIs
