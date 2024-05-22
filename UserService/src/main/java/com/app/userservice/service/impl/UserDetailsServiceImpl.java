package com.app.userservice.service.impl;

import com.app.userservice.entity.User;
import com.app.userservice.exception.RecordNotFoundException;
import com.app.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
   private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username)
                .orElseThrow(()-> new RecordNotFoundException("No User with this email "+username));
        return user;

    }
}
