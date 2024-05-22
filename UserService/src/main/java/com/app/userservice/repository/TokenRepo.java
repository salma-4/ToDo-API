package com.app.userservice.repository;
import com.app.userservice.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<Token,Long> {
    Token findByToken(String token);

}
