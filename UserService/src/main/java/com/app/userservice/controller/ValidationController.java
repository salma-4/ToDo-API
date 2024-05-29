package com.app.userservice.controller;

import com.app.userservice.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/auth/validation")
public class ValidationController {
    private  final UserService userService;

    @Operation(summary = "Check validty of token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK,Valid")
            ,@ApiResponse(responseCode = "403",description = "FORBIDDEN, CHECK YOUR TOKEN")
    })
    @GetMapping()
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String tokenHeader){
        System.out.println(tokenHeader);
        String message = userService.checkTokenValidty(tokenHeader);
        return ResponseEntity.ok(message);
    }


}
