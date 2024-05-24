package com.app.userservice.service.impl;

import com.app.userservice.entity.Otp;
import com.app.userservice.entity.User;
import com.app.userservice.mapper.OtpMapper;
import com.app.userservice.model.response.OtpResponseDTO;
import com.app.userservice.repository.OTPRepo;
import com.app.userservice.service.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class OtpServiceImpl implements OtpService {
    private final OTPRepo otpRepo;
    private final OtpMapper otpMapper;

    private final int OTP_LENGTH =6;

    @Override
    public OtpResponseDTO generateOtp(User user) {
        String otpCode = generateNumericOtp(OTP_LENGTH);
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(60);
        Otp otp = new Otp();
        otp.setOtp(otpCode);
        otp.setExpirationTime(expirationTime);
        otp.setUser(user);
       otpRepo.save(otp);
       return otpMapper.toResponseDTO(
               otp
       );

    }
   //TODO user have more than one
   @Override
   public boolean validateOtp(OtpResponseDTO otp) {
       Otp storedOtp = otpRepo.findTopByUserEmailOrderByExpirationTimeDesc(otp.getUserEmail());
       return storedOtp != null &&
               storedOtp.getOtp().equals(otp.getOtp()) &&
               storedOtp.getExpirationTime() != null &&
               storedOtp.getExpirationTime().isAfter(LocalDateTime.now());
   }

    private String generateNumericOtp(int length) {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }
}
