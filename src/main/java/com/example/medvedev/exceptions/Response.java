package com.example.medvedev.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class Response {
    private final String message;
    private final HttpStatus status;

    public  Response(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
