package com.app.todoservice.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ToDoExceptionResponse {

    private final String message;
    private final int statusCode;
    private final Date timeStamp;
}
