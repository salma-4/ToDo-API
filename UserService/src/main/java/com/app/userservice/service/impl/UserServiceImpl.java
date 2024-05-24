package com.app.userservice.service.impl;

import com.app.userservice.entity.User;
import com.app.userservice.exception.ConflicException;
import com.app.userservice.exception.RecordNotFoundException;

import com.app.userservice.model.request.UserRequestDTO;
import com.app.userservice.repository.UserRepository;
import com.app.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder encoder;

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
}
