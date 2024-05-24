package com.app.userservice.controller;


import com.app.userservice.model.request.LoginRequest;
import com.app.userservice.model.request.UserRequestDTO;
import com.app.userservice.model.response.AuthResponse;
import com.app.userservice.model.response.OtpResponseDTO;
import com.app.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/auth")
public class AuthController {
    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
      AuthResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/register")
    public ResponseEntity<OtpResponseDTO> register(@RequestBody UserRequestDTO user){
        OtpResponseDTO otp = authService.register(user);
       return new ResponseEntity<>(otp,HttpStatus.CREATED);
    }
    @GetMapping("/activate")
    public String activateUser(@RequestParam String username, @RequestHeader("OTP") String otp) {
        return authService.activateAccount(otp,username);
    }
}
