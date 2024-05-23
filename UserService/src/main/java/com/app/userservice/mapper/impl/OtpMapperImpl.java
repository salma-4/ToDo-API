package com.app.userservice.mapper.impl;

import com.app.userservice.entity.Otp;
import com.app.userservice.mapper.OtpMapper;
import com.app.userservice.model.response.OtpResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class OtpMapperImpl implements OtpMapper {
    @Override
    public OtpResponseDTO toResponseDTO(Otp otp) {
        return OtpResponseDTO.builder()
                .otp(otp.getOtp())
                .userEmail(otp.getUser().getEmail())
                .build();
    }
}
