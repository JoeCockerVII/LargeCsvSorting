package com.example.csvsorting.domain.exception;


public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message + "not found ");
    }
}

