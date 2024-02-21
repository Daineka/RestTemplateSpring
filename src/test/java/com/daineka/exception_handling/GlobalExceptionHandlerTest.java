package com.daineka.exception_handling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void handleNoSuchException() {
        NoSuchException exception = new NoSuchException("Resource not found");

        ResponseEntity<IncorrectData> response = handler.handleException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Resource not found", Objects.requireNonNull(response.getBody()).getInfo());
    }

    @Test
    void handleOtherExceptions() {
        Exception exception = new Exception("Some error occurred");

        ResponseEntity<IncorrectData> response = handler.handleException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Some error occurred", Objects.requireNonNull(response.getBody()).getInfo());
    }
}