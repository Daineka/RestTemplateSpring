package com.daineka.service.mapper;

import com.daineka.entity.Author;
import com.daineka.entity.Book;
import com.daineka.entity.Genre;
import com.daineka.service.dto.AuthorDTO;
import com.daineka.service.dto.BookWithAuthorAndGenresDTO;
import com.daineka.service.dto.GenreDTO;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookWithAuthorAndGenresMapperTest {

    private final BookWithAuthorAndGenresMapper bookWithAuthorAndGenresMapper = new BookWithAuthorAndGenresMapperImpl();

    @Test
    void testToDto() {
        Author author = new Author(1L, "John Doe");

        Genre genre = new Genre(1L, "Fiction");

        Book book = new Book(1L, "Book Title", 2022, author, Set.of(genre));

        BookWithAuthorAndGenresDTO bookDTO = bookWithAuthorAndGenresMapper.toDto(book);

        assertEquals(book.getId(), bookDTO.id());
        assertEquals(book.getTitle(), bookDTO.title());
        assertEquals(book.getPublishedYear(), bookDTO.publishedYear());
        assertEquals(book.getAuthor().getId(), bookDTO.authorDTO().id());
        assertEquals(book.getGenres().size(), bookDTO.genreDTOs().size());
    }

    @Test
    void testToEntity() {
        AuthorDTO authorDTO = new AuthorDTO(1L, "John Doe");

        GenreDTO genreDTO = new GenreDTO(1L, "Fiction");

        BookWithAuthorAndGenresDTO bookWithAuthorAndGenresDTO = new BookWithAuthorAndGenresDTO(1L, "Book Title", 2022, authorDTO, Set.of(genreDTO));

        Book book = bookWithAuthorAndGenresMapper.toEntity(bookWithAuthorAndGenresDTO);

        assertEquals(bookWithAuthorAndGenresDTO.id(), book.getId());
        assertEquals(bookWithAuthorAndGenresDTO.title(), book.getTitle());
        assertEquals(bookWithAuthorAndGenresDTO.publishedYear(), book.getPublishedYear());
        assertEquals(bookWithAuthorAndGenresDTO.authorDTO().id(), book.getAuthor().getId());
        assertEquals(bookWithAuthorAndGenresDTO.genreDTOs().size(), book.getGenres().size());
    }
}