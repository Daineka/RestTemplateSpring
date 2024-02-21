package com.daineka.service.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenreDTOTest {

    @Test
    void testGenreDTO() {
        GenreDTO genreDTO = new GenreDTO(1L, "Fantasy");

        assertEquals(1L, genreDTO.id());
        assertEquals("Fantasy", genreDTO.name());
    }

    @Test
    void testGetters() {
        GenreDTO genreDTO = new GenreDTO(2L, "Science Fiction");

        assertEquals(2L, genreDTO.id());
        assertEquals("Science Fiction", genreDTO.name());
    }

    @Test
    void testEquality() {
        GenreDTO genreDTO1 = new GenreDTO(1L, "Fantasy");
        GenreDTO genreDTO2 = new GenreDTO(1L, "Fantasy");

        assertEquals(genreDTO1, genreDTO2);
    }

    @Test
    void testToString() {
        GenreDTO genreDTO = new GenreDTO(3L, "Mystery");

        assertEquals("GenreDTO[id=3, name=Mystery]", genreDTO.toString());
    }
}