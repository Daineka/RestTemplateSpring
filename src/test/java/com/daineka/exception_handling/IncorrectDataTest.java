package com.daineka.exception_handling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IncorrectDataTest {

    @Test
    void testSetAndGetInfo() {
        String info = "Test info";
        IncorrectData incorrectData = new IncorrectData();

        incorrectData.setInfo(info);

        assertEquals(info, incorrectData.getInfo());
    }
}