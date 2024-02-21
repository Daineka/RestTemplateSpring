package com.daineka.exception_handling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoSuchExceptionTest {

    @Test
    void testConstructorAndGetMessage() {
        String message = "This is a test message.";

        NoSuchException exception = new NoSuchException(message);

        assertEquals(message, exception.getMessage());
    }
}