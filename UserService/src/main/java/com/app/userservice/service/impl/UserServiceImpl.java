package com.app.userservice.service.impl;

import com.app.userservice.entity.User;
import com.app.userservice.exception.RecordNotFoundException;
import com.app.userservice.mapper.UserMapper;
import com.app.userservice.model.UserResponseDTO;
import com.app.userservice.repository.UserRepository;
import com.app.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private  final UserMapper userMapper;
    private  final UserRepository userRepository;
    @Override
    public UserResponseDTO findUserByEmail(String email) {
        log.info("trying to find user with email");
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(()->new RecordNotFoundException("user with email "+email+"not found"));
        UserResponseDTO response = userMapper.toDTO(user);
        log.info("User with email {} : {}",email, response);
        return response;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        log.info("try to get all users");
        List<UserResponseDTO> users = userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
        if(users.isEmpty())
        {
            log.error("No users found");
            throw new RecordNotFoundException("no users found");
        }
        log.info("All users {}", users);
        return users;
    }
}
