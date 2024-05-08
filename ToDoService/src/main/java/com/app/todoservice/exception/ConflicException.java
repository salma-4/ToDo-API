package com.app.todoservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConflicException extends RuntimeException {
    public ConflicException(String message) {
        super(message);
    }
}
