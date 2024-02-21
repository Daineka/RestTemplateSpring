package com.daineka.service.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorDTOTest {

    @Test
    void testAuthorDTO() {
        AuthorDTO authorDTO = new AuthorDTO(1L, "John Doe");

        assertEquals(1L, authorDTO.id());
        assertEquals("John Doe", authorDTO.name());
    }

    @Test
    void testGetters() {
        AuthorDTO authorDTO = new AuthorDTO(2L, "Jane Smith");

        assertEquals(2L, authorDTO.id());
        assertEquals("Jane Smith", authorDTO.name());
    }

    @Test
    void testEquality() {
        AuthorDTO authorDTO1 = new AuthorDTO(1L, "John Doe");
        AuthorDTO authorDTO2 = new AuthorDTO(1L, "John Doe");

        assertEquals(authorDTO1, authorDTO2);
    }

    @Test
    void testToString() {
        AuthorDTO authorDTO = new AuthorDTO(3L, "Alice Johnson");

        assertEquals("AuthorDTO[id=3, name=Alice Johnson]", authorDTO.toString());
    }
}