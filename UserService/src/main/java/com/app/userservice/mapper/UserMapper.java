package com.app.userservice.mapper;


import com.app.userservice.entity.User;
import com.app.userservice.model.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toDTO(User user);
}