package com.app.userservice.service;

import com.app.userservice.model.request.LoginRequest;
import com.app.userservice.model.request.UserRequestDTO;
import com.app.userservice.model.response.AuthResponse;

public interface AuthService {

    AuthResponse login (LoginRequest loginRequest);
    String register(UserRequestDTO user);
}
