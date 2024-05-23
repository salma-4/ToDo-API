package com.app.userservice.mapper;

import com.app.userservice.entity.Otp;
import com.app.userservice.model.response.OtpResponseDTO;

public  interface OtpMapper {

    OtpResponseDTO toResponseDTO(Otp otp);

}
