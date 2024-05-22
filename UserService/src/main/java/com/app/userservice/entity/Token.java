package com.app.userservice.entity;

import com.app.userservice.entity.enums.TokenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "jwt")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Token
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public Date createdAT;

    public Date expirationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
}
