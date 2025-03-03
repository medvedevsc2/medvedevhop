    package com.example.medvedev.exceptions;

    public class ShoeNotFoundException extends RuntimeException {
        public ShoeNotFoundException(String message) {
            super(message);
        }
    }
