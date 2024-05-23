package com.app.userservice.service.impl;

import com.app.userservice.entity.Token;
import com.app.userservice.entity.User;
import com.app.userservice.exception.ConflicException;
import com.app.userservice.exception.InvalidOtpException;
import com.app.userservice.exception.RecordNotFoundException;
import com.app.userservice.mapper.TokenMapper;
import com.app.userservice.mapper.UserMapper;
import com.app.userservice.model.request.LoginRequest;
import com.app.userservice.model.request.UserRequestDTO;
import com.app.userservice.model.response.AuthResponse;
import com.app.userservice.model.response.OtpResponseDTO;
import com.app.userservice.repository.TokenRepo;
import com.app.userservice.repository.UserRepository;
import com.app.userservice.service.AuthService;
import com.app.userservice.service.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
   private final UserRepository userRepository;
   private final UserMapper userMapper;
   private final BCryptPasswordEncoder passwordEncoder;
   private final OtpService otpService;
   private final TokenRepo tokenRepo;
   private final TokenMapper tokenMapper;
   private final JwtService jwtService;
   private final AuthenticationManager authenticationManager;
    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        User user =userRepository.findUserByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new RecordNotFoundException("No user with email "+loginRequest.getEmail()));
        String userToken = jwtService.generateToken(user);
        Token token = tokenMapper.toEntity(user,userToken);
        tokenRepo.save(token);
        return new AuthResponse(userToken,user.getEmail());
    }

    @Override
    public OtpResponseDTO register(UserRequestDTO user) {
      User newUser = userMapper.toEntity(user);
      Optional<User> existingUser =userRepository.findUserByEmail(user.getEmail());
       if(!existingUser.isEmpty()) {
           System.out.println(existingUser.toString());
           throw new ConflicException("Email " + user.getEmail() + " already have todo account");

       }
       userRepository.save(newUser);
       OtpResponseDTO otp = otpService.generateOtp(newUser);
        return otp;
    }

    @Override
    public String activateAccount(OtpResponseDTO otp) {
        User user = userRepository.findUserByEmail(otp.getUserEmail())
                .orElseThrow(()-> new RecordNotFoundException("No user with email"+otp.getUserEmail()));
        if(otpService.validateOtp(otp)){
            user.setEnabled(true);
            userRepository.save(user);
            return "Your account successfully activated";
        }
        throw new InvalidOtpException("Invalid Or expired otp ");

    }
}
