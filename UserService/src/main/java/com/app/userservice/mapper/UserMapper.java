package com.app.userservice.mapper;


import com.app.userservice.entity.User;
import com.app.userservice.model.request.UserRequestDTO;
import com.app.userservice.model.response.UserResponseDTO;
import org.mapstruct.Mapper;
public interface UserMapper {
    UserResponseDTO toDTO(User user);
    User toEntity(UserRequestDTO user);
}