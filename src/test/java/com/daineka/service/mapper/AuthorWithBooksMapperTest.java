package com.daineka.service.mapper;

import com.daineka.entity.Author;
import com.daineka.entity.Book;
import com.daineka.service.dto.AuthorWithBooksDTO;
import com.daineka.service.dto.BookDTO;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorWithBooksMapperTest {

    private final AuthorWithBooksMapper authorWithBooksMapper = new AuthorWithBooksMapperImpl();

    @Test
    void testToDto() {
        Author author = new Author(1L, "John Doe");

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book Title");
        book.setPublishedYear(2022);

        Set<Book> books = new HashSet<>();
        books.add(book);
        author.setBooks(books);

        AuthorWithBooksDTO authorDTO = authorWithBooksMapper.toDto(author);

        assertEquals(author.getId(), authorDTO.id());
        assertEquals(author.getName(), authorDTO.name());
        assertEquals(author.getBooks().size(), authorDTO.booksDTO().size());
    }

    @Test
    void testToEntity() {
        AuthorWithBooksDTO authorDTO;

        BookDTO bookDTO = new BookDTO(1L, "Book Title", 2022, 1L);

        Set<BookDTO> booksDTO = new HashSet<>();
        booksDTO.add(bookDTO);
        authorDTO = new AuthorWithBooksDTO(1L, "John Doe", booksDTO);

        Author author = authorWithBooksMapper.toEntity(authorDTO);

        assertEquals(authorDTO.id(), author.getId());
        assertEquals(authorDTO.name(), author.getName());
        assertEquals(authorDTO.booksDTO().size(), author.getBooks().size());
    }
}