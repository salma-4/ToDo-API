package com.app.userservice.mapper;

import com.app.userservice.entity.Token;
import com.app.userservice.entity.User;

public interface TokenMapper {
    Token toEntity(User user, String  token);
}
