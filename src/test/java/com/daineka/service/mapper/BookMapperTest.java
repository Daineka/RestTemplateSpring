package com.daineka.service.mapper;

import com.daineka.entity.Author;
import com.daineka.entity.Book;
import com.daineka.service.dto.BookDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookMapperTest {

    private final BookMapper bookMapper = new BookMapperImpl();

    @Test
    void testToEntity() {
        BookDTO bookDTO = new BookDTO(1L, "The Great Gatsby", 1925, 1L);

        Book book = bookMapper.toEntity(bookDTO);

        assertEquals(bookDTO.id(), book.getId());
        assertEquals(bookDTO.title(), book.getTitle());
        assertEquals(bookDTO.publishedYear(), book.getPublishedYear());
        assertEquals(bookDTO.authorId(), book.getAuthor().getId());
    }

    @Test
    void testToDto() {
        Author author = new Author(1L, "name");
        Book book = new Book(1L, "The Great Gatsby", 1925, author);

        BookDTO bookDTO = bookMapper.toDto(book);

        assertEquals(book.getId(), bookDTO.id());
        assertEquals(book.getTitle(), bookDTO.title());
        assertEquals(book.getPublishedYear(), bookDTO.publishedYear());
        assertEquals(book.getAuthor().getId(), bookDTO.authorId());
    }
}