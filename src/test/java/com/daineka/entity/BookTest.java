package com.daineka.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BookTest {

    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book(1L, "Test Book", 2022, new Author());
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(1L, book.getId());
        assertEquals("Test Book", book.getTitle());
        assertEquals(2022, book.getPublishedYear());
        assertNotNull(book.getGenres());
        assertNotNull(book.getAuthor());
    }

    @Test
    void testSetterAndGetters() {
        Author author = new Author(2L, "Jane Smith");
        Set<Genre> genres = new HashSet<>();
        Genre genre = new Genre(1L, "Fiction");
        genres.add(genre);

        book.setId(2L);
        book.setTitle("Updated Title");
        book.setPublishedYear(2023);
        book.setAuthor(author);
        book.setGenres(genres);

        assertEquals(2L, book.getId());
        assertEquals("Updated Title", book.getTitle());
        assertEquals(2023, book.getPublishedYear());
        assertEquals(author, book.getAuthor());
        assertEquals(genres, book.getGenres());
    }

    @Test
    void testEqualsAndHashCode() {
        Author author1 = new Author(1L, "John Doe");
        Book sameBook = new Book(1L, "Test Book", 2022, author1);
        Book differentBook = new Book(2L, "Different Book", 2023, author1);

        assertEquals(book, sameBook);
        assertEquals(book.hashCode(), sameBook.hashCode());

        assertNotEquals(book, differentBook);
        assertNotEquals(book.hashCode(), differentBook.hashCode());
    }

    @Test
    void testToString() {
        String expectedToString = "Book{id=1, title='Test Book', publishedYear=2022, author=null}";
        assertEquals(expectedToString, book.toString());
    }
}