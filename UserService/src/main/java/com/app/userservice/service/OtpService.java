package com.app.userservice.service;

import com.app.userservice.entity.User;
import com.app.userservice.model.response.OtpResponseDTO;

public interface OtpService {

     OtpResponseDTO generateOtp(User user);
     boolean validateOtp(OtpResponseDTO otp);
}
