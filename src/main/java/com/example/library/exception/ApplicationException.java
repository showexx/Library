package com.example.library.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
public class ApplicationException {
    private int status;
    private String message;
    private Date timestamp;

    public ApplicationException(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
