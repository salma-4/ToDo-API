package com.app.userservice.exception;

import com.app.userservice.exception.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {RecordNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleItemNotFoundException(RecordNotFoundException e) {
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ConflicException.class})
    public ResponseEntity<ExceptionResponse> handleConflictException(ConflicException e) {
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                new Date());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = {InvalidOtpException.class})
    public ResponseEntity<ExceptionResponse> handleInvalidOtpException(InvalidOtpException e){
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
