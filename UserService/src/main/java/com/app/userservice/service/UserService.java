package com.app.userservice.service;

import com.app.userservice.model.UserResponseDTO;

import java.util.List;

public interface UserService {
 UserResponseDTO findUserByEmail(String email);
 List<UserResponseDTO> getAllUsers();
}
