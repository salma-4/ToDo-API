package com.app.userservice.service;

import com.app.userservice.model.request.LoginRequest;
import com.app.userservice.model.request.UserRequestDTO;
import com.app.userservice.model.response.AuthResponse;
import com.app.userservice.model.response.OtpResponseDTO;

public interface AuthService {

    AuthResponse login (LoginRequest loginRequest);
    OtpResponseDTO register(UserRequestDTO user);
    String activateAccount(String otpCode,String email);
}
