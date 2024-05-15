package com.app.userservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConflicException extends RuntimeException {
    public ConflicException(String message) {
        super(message);
    }
}
