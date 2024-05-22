package com.app.userservice.repository;

import com.app.userservice.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OTPRepo extends JpaRepository<Otp,Long> {
    Otp findByOtp(String otp);
}
