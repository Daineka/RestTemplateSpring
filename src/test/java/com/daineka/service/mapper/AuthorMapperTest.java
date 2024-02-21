package com.daineka.service.mapper;

import com.daineka.entity.Author;
import com.daineka.service.dto.AuthorDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorMapperTest {

    private final AuthorMapper authorMapper = new AuthorMapperImpl();

    @Test
    void testToEntity() {
        AuthorDTO authorDTO = new AuthorDTO(1L, "John Doe");

        Author author = authorMapper.toEntity(authorDTO);

        assertEquals(authorDTO.id(), author.getId());
        assertEquals(authorDTO.name(), author.getName());
    }

    @Test
    void testToDto() {
        Author author = new Author(1L, "John Doe");

        AuthorDTO authorDTO = authorMapper.toDto(author);

        assertEquals(author.getId(), authorDTO.id());
        assertEquals(author.getName(), authorDTO.name());
    }
}