package com.app.userservice.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ExceptionResponse {

    private final String message;
    private final int statusCode;
    private final Date timeStamp;
}
