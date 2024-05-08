package com.app.todoservice.exception;

import com.app.todoservice.exception.response.ToDoExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ItemNotFoundException.class})
    public ResponseEntity<ToDoExceptionResponse> handleItemNotFoundException(ItemNotFoundException e) {
        ToDoExceptionResponse response = new ToDoExceptionResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ConflicException.class})
    public ResponseEntity<ToDoExceptionResponse> handleConflictException(ConflicException e) {
        ToDoExceptionResponse response = new ToDoExceptionResponse(
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                new Date());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
