package com.daineka.service.mapper;

import com.daineka.entity.Genre;
import com.daineka.service.dto.GenreDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenreMapperTest {

    private final GenreMapper genreMapper = new GenreMapperImpl();

    @Test
    void testToEntity() {
        GenreDTO genreDTO = new GenreDTO(1L, "Fantasy");

        Genre genre = genreMapper.toEntity(genreDTO);

        assertEquals(genreDTO.id(), genre.getId());
        assertEquals(genreDTO.name(), genre.getName());
    }

    @Test
    void testToDto() {
        Genre genre = new Genre(1L, "Fantasy");

        GenreDTO genreDTO = genreMapper.toDto(genre);

        assertEquals(genre.getId(), genreDTO.id());
        assertEquals(genre.getName(), genreDTO.name());
    }
}