package com.app.userservice.controller;

import com.app.userservice.model.request.LoginRequest;
import com.app.userservice.model.request.UserRequestDTO;
import com.app.userservice.model.response.AuthResponse;
import com.app.userservice.model.response.OtpResponseDTO;
import com.app.userservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Login with email and password ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK,token generated it last for an hour ")
            ,@ApiResponse(responseCode = "404",description = "Not found , no user with email entered")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Add new user ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" ,description = "CREATED, and otp send to user email ")
            ,@ApiResponse(responseCode = "409",description = "CONFLICT , user email already exist")
    })
    @PostMapping("/register")
    public ResponseEntity<OtpResponseDTO> register(@RequestBody UserRequestDTO user) {
        OtpResponseDTO otp = authService.register(user);
        return new ResponseEntity<>(otp, HttpStatus.CREATED);
    }
    @Operation(summary = "Activate user via OTP ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, User activated enabled = true ")
            ,@ApiResponse(responseCode = "400",description = "BAD_REQUEST, OTP may be expired or invalid")
    })
    @GetMapping("/activate")
    public ResponseEntity<String> activateUser(@RequestParam String username, @RequestHeader("OTP") String otp) {
        String message = authService.activateAccount(otp, username);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
