package com.app.userservice.mapper.impl;

import com.app.userservice.entity.Token;
import com.app.userservice.entity.User;
import com.app.userservice.entity.enums.TokenType;
import com.app.userservice.mapper.TokenMapper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenMapperImpl  implements TokenMapper {
    private final long ACCESS_TOKEN_VALIDTY  =60 *60*1000; // 1 hour
    @Override
    public Token toEntity(User user, String token) {
       return Token.builder()
               .user(user)
               .token(token)
               .tokenType(TokenType.BEARER)
               .expirationDate(new Date(System.currentTimeMillis()+ACCESS_TOKEN_VALIDTY))
               .createdAT(new Date(System.currentTimeMillis()))
               .build();
    }
}
