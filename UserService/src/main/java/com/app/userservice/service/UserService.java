package com.app.userservice.service;

import com.app.userservice.model.request.UserRequestDTO;
import com.app.userservice.model.response.UserResponseDTO;

import java.util.List;

public interface UserService {

     String activateUser(long id);
     String deactivateUser(long id);
     String checkTokenValidty(String headerToken);
     String updateUser(UserRequestDTO user,long id );
     String deleteUser(long id);
     List<UserResponseDTO> getAllUser();
     UserResponseDTO getUserByEmail(String email);
     String forgetPassword(String token);
     String changePassword(String token, String otp, String newPassword);
     String regenerateOtp(String email);


}
