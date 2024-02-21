package com.daineka.service.dto;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthorWithBooksDTOTest {

    @Test
    void testAuthorWithBooksDTO() {
        Set<BookDTO> booksDTO = new HashSet<>();
        booksDTO.add(new BookDTO(1L, "Book1", 2020, 1L));
        booksDTO.add(new BookDTO(2L, "Book2", 2021, 1L));

        AuthorWithBooksDTO authorWithBooksDTO = new AuthorWithBooksDTO(1L, "Author1", booksDTO);

        assertEquals(1L, authorWithBooksDTO.id());
        assertEquals("Author1", authorWithBooksDTO.name());
        assertEquals(2, authorWithBooksDTO.booksDTO().size());
        assertTrue(authorWithBooksDTO.booksDTO().contains(new BookDTO(1L, "Book1", 2020, 1L)));
        assertTrue(authorWithBooksDTO.booksDTO().contains(new BookDTO(2L, "Book2", 2021, 1L)));
    }

    @Test
    void testGetters() {
        Set<BookDTO> booksDTO = new HashSet<>();
        booksDTO.add(new BookDTO(3L, "Book3", 2019, 2L));

        AuthorWithBooksDTO authorWithBooksDTO = new AuthorWithBooksDTO(2L, "Author2", booksDTO);

        assertEquals(2L, authorWithBooksDTO.id());
        assertEquals("Author2", authorWithBooksDTO.name());
        assertEquals(1, authorWithBooksDTO.booksDTO().size());
        assertTrue(authorWithBooksDTO.booksDTO().contains(new BookDTO(3L, "Book3", 2019, 2L)));
    }

    @Test
    void testEquality() {
        Set<BookDTO> booksDTO1 = new HashSet<>();
        booksDTO1.add(new BookDTO(4L, "Book4", 2018, 3L));

        Set<BookDTO> booksDTO2 = new HashSet<>();
        booksDTO2.add(new BookDTO(4L, "Book4", 2018, 3L));

        AuthorWithBooksDTO authorWithBooksDTO1 = new AuthorWithBooksDTO(3L, "Author3", booksDTO1);
        AuthorWithBooksDTO authorWithBooksDTO2 = new AuthorWithBooksDTO(3L, "Author3", booksDTO2);

        assertEquals(authorWithBooksDTO1, authorWithBooksDTO2);
    }

    @Test
    void testToString() {
        Set<BookDTO> booksDTO = new HashSet<>();
        booksDTO.add(new BookDTO(5L, "Book5", 2017, 4L));

        AuthorWithBooksDTO authorWithBooksDTO = new AuthorWithBooksDTO(4L, "Author4", booksDTO);

        assertEquals("AuthorWithBooksDTO[id=4, name=Author4, booksDTO=[BookDTO[id=5, title=Book5, publishedYear=2017, authorId=4]]]", authorWithBooksDTO.toString());
    }
}