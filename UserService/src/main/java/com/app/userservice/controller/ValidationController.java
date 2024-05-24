package com.app.userservice.controller;

import com.app.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("user/token/validation")
public class ValidationController {
    private  final UserService userService;


    @GetMapping()
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String tokenHeader){

        String message = userService.checkTokenValidty(tokenHeader);
        return ResponseEntity.ok(message);
    }


}
