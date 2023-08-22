package com.example.library.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationException extends RuntimeException {
    private final int status;

    public ApplicationException(int status, String message) {
        super(message);
        this.status = status;
    }
}