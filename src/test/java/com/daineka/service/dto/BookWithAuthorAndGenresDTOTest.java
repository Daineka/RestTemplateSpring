package com.daineka.service.dto;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookWithAuthorAndGenresDTOTest {

    @Test
    void testBookWithAuthorAndGenresDTO() {
        Set<GenreDTO> genreDTOs = new HashSet<>();
        genreDTOs.add(new GenreDTO(1L, "Genre1"));
        genreDTOs.add(new GenreDTO(2L, "Genre2"));

        AuthorDTO authorDTO = new AuthorDTO(1L, "Author1");
        BookWithAuthorAndGenresDTO bookWithAuthorAndGenresDTO = new BookWithAuthorAndGenresDTO(1L, "Book1", 2022, authorDTO, genreDTOs);

        assertEquals(1L, bookWithAuthorAndGenresDTO.id());
        assertEquals("Book1", bookWithAuthorAndGenresDTO.title());
        assertEquals(2022, bookWithAuthorAndGenresDTO.publishedYear().intValue());
        assertEquals(authorDTO, bookWithAuthorAndGenresDTO.authorDTO());
        assertEquals(2, bookWithAuthorAndGenresDTO.genreDTOs().size());
        assertTrue(bookWithAuthorAndGenresDTO.genreDTOs().contains(new GenreDTO(1L, "Genre1")));
        assertTrue(bookWithAuthorAndGenresDTO.genreDTOs().contains(new GenreDTO(2L, "Genre2")));
    }

    @Test
    void testGetters() {
        Set<GenreDTO> genreDTOs = new HashSet<>();
        genreDTOs.add(new GenreDTO(3L, "Genre3"));

        AuthorDTO authorDTO = new AuthorDTO(2L, "Author2");
        BookWithAuthorAndGenresDTO bookWithAuthorAndGenresDTO = new BookWithAuthorAndGenresDTO(2L, "Book2", 2023, authorDTO, genreDTOs);

        assertEquals(2L, bookWithAuthorAndGenresDTO.id());
        assertEquals("Book2", bookWithAuthorAndGenresDTO.title());
        assertEquals(2023, bookWithAuthorAndGenresDTO.publishedYear().intValue());
        assertEquals(authorDTO, bookWithAuthorAndGenresDTO.authorDTO());
        assertEquals(1, bookWithAuthorAndGenresDTO.genreDTOs().size());
        assertTrue(bookWithAuthorAndGenresDTO.genreDTOs().contains(new GenreDTO(3L, "Genre3")));
    }

    @Test
    void testEquality() {
        Set<GenreDTO> genreDTOs1 = new HashSet<>();
        genreDTOs1.add(new GenreDTO(4L, "Genre4"));

        Set<GenreDTO> genreDTOs2 = new HashSet<>();
        genreDTOs2.add(new GenreDTO(4L, "Genre4"));

        AuthorDTO authorDTO1 = new AuthorDTO(3L, "Author3");
        BookWithAuthorAndGenresDTO bookWithAuthorAndGenresDTO1 = new BookWithAuthorAndGenresDTO(3L, "Book3", 2024, authorDTO1, genreDTOs1);
        BookWithAuthorAndGenresDTO bookWithAuthorAndGenresDTO2 = new BookWithAuthorAndGenresDTO(3L, "Book3", 2024, authorDTO1, genreDTOs2);

        assertEquals(bookWithAuthorAndGenresDTO1, bookWithAuthorAndGenresDTO2);
    }

    @Test
    void testToString() {
        Set<GenreDTO> genreDTOs = new HashSet<>();
        genreDTOs.add(new GenreDTO(5L, "Genre5"));

        AuthorDTO authorDTO = new AuthorDTO(4L, "Author4");
        BookWithAuthorAndGenresDTO bookWithAuthorAndGenresDTO = new BookWithAuthorAndGenresDTO(4L, "Book4", 2025, authorDTO, genreDTOs);

        assertEquals("BookWithAuthorAndGenresDTO[id=4, title=Book4, publishedYear=2025, authorDTO=AuthorDTO[id=4, name=Author4], genreDTOs=[GenreDTO[id=5, name=Genre5]]]", bookWithAuthorAndGenresDTO.toString());
    }
}