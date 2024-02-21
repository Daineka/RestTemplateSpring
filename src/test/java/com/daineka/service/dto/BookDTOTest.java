package com.daineka.service.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookDTOTest {

    @Test
    void testBookDTO() {
        BookDTO bookDTO = new BookDTO(1L, "Book Title", 2022, 2L);

        assertEquals(1L, bookDTO.id());
        assertEquals("Book Title", bookDTO.title());
        assertEquals(2022, bookDTO.publishedYear().intValue());
        assertEquals(2L, bookDTO.authorId().longValue());
    }

    @Test
    void testGetters() {
        BookDTO bookDTO = new BookDTO(2L, "Another Book", 2010, 3L);

        assertEquals(2L, bookDTO.id());
        assertEquals("Another Book", bookDTO.title());
        assertEquals(2010, bookDTO.publishedYear().intValue());
        assertEquals(3L, bookDTO.authorId().longValue());
    }

    @Test
    void testEquality() {
        BookDTO bookDTO1 = new BookDTO(1L, "Book1", 2020, 4L);
        BookDTO bookDTO2 = new BookDTO(1L, "Book1", 2020, 4L);

        assertEquals(bookDTO1, bookDTO2);
    }

    @Test
    void testToString() {
        BookDTO bookDTO = new BookDTO(3L, "Great Book", 2015, 5L);

        assertEquals("BookDTO[id=3, title=Great Book, publishedYear=2015, authorId=5]", bookDTO.toString());
    }
}
