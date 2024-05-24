package com.app.userservice.service.impl;

import com.app.userservice.entity.Otp;
import com.app.userservice.entity.User;
import com.app.userservice.exception.ConflicException;
import com.app.userservice.exception.RecordNotFoundException;

import com.app.userservice.mapper.OtpMapper;
import com.app.userservice.mapper.UserMapper;
import com.app.userservice.model.request.UserRequestDTO;
import com.app.userservice.model.response.OtpResponseDTO;
import com.app.userservice.model.response.UserResponseDTO;
import com.app.userservice.repository.OTPRepo;
import com.app.userservice.repository.UserRepository;
import com.app.userservice.service.OtpService;
import com.app.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder encoder;
   private final UserMapper userMapper;
   private  final OtpService otpService;
   private final EmailService emailService;
   private final OTPRepo otpRepo;
   private final OtpMapper otpMapper;
    @Override
    public String activateUser(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RecordNotFoundException("no user with id : "+id));
        boolean exist =user.isEnabled();
        if(exist)
            return "user active already";
        user.setEnabled(true);
        userRepository.save(user);
        return "user activated";
    }

    @Override
    public String deactivateUser(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RecordNotFoundException("no user with id : "+id));
        boolean exist =user.isEnabled();
        if( !exist)
            return "user already not active";
        user.setEnabled(false);
        userRepository.save(user);
        return "user deactivated";
    }

    @Override
    public String checkTokenValidty(String headerToken) {
       String token = headerToken.replace("Bearer ", "");
        UserDetails userDetails = jwtService.getCurrentUserDetails();
       boolean isValid = jwtService.isTokenValid(token,userDetails);
       return isValid ? "valid token" :"invalid token";
    }

    @Override
    public String updateUser(UserRequestDTO updateUser,long id) {
        if(id>0){
         User user = userRepository.findById(id)
                 .orElseThrow(()-> new RecordNotFoundException("No user with id "+id));
         Optional<User> existingUser =userRepository.findUserByEmail(updateUser.getEmail());
         if(!existingUser.isEmpty())
             throw new ConflicException("Updated email is  already exist");

         user.setEmail(updateUser.getEmail());
         user.setPassword(encoder.encode(updateUser.getPassword()));
         userRepository.save(user);
         return "User updated successfully";
    }
        throw new IllegalArgumentException("Invalid id "+id);
    }

    @Override
    public String deleteUser(long id) {
        if(id>0){
            User user = userRepository.findById(id)
                    .orElseThrow(()-> new RecordNotFoundException("No user with id"));
            userRepository.deleteById(id);
        }
        throw new IllegalArgumentException("Invalid id "+id);
    }

    @Override
    public List<UserResponseDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(user -> userMapper.toDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserByEmail(String email) {
       User user = userRepository.findUserByEmail(email)
               .orElseThrow(()-> new RecordNotFoundException("No user with email "+email));
       return userMapper.toDTO(user);
    }

    @Override
    public String forgetPassword(String headerToken) {
        String token = headerToken.replace("Bearer ", "");
        String email = jwtService.extractUsername(token);
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(()-> new RecordNotFoundException("no user with emaill"));
        OtpResponseDTO otp = otpService.generateOtp(user);
        emailService.sendOtpEmail("salmasobhy456@gmail.com",otp.getOtp());
        return "check your email for verification code";

    }

    @Override
    public String changePassword(String headerToken, String otp, String newPassword) {
        String token = headerToken.replace("Bearer ", "");
        String email = jwtService.extractUsername(token);
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RecordNotFoundException("User not found"));
        Otp validOtp =otpRepo.findByOtp(otp);
         if (otpService.validateOtp(otpMapper.toResponseDTO(validOtp))){
        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
        return "Password changed successfully";
         }
         return "Expired otp regenerate it";
    }

    @Override
    public String regenerateOtp(String email) {
     User user=   userRepository.findUserByEmail(email)
             .orElseThrow(()-> new RecordNotFoundException("No user with email"+email));
            OtpResponseDTO otp =otpService.generateOtp(user);
        emailService.sendOtpEmail("salmasobhy456@gmail.com",otp.getOtp());
        return "check your email for new verification code";
    }


}
