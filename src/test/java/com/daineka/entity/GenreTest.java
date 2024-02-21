package com.daineka.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GenreTest {

    private Genre genre;

    @BeforeEach
    public void setUp() {
        genre = new Genre(1L, "Fiction");
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(1L, genre.getId());
        assertEquals("Fiction", genre.getName());
        assertNotNull(genre.getBooks());
    }

    @Test
    void testSetterAndGetters() {
        Set<Book> books = new HashSet<>();
        Book book1 = new Book();
        Book book2 = new Book();
        books.add(book1);
        books.add(book2);

        genre.setId(2L);
        genre.setName("Non-fiction");
        genre.setBooks(books);

        assertEquals(2L, genre.getId());
        assertEquals("Non-fiction", genre.getName());
        assertEquals(books, genre.getBooks());
    }

    @Test
    void testEqualsAndHashCode() {
        Genre sameGenre = new Genre(1L, "Fiction");
        Genre differentGenre = new Genre(2L, "Non-fiction");

        assertEquals(genre, sameGenre);
        assertEquals(genre.hashCode(), sameGenre.hashCode());

        assertNotEquals(genre, differentGenre);
        assertNotEquals(genre.hashCode(), differentGenre.hashCode());
    }

    @Test
    void testToString() {
        String expectedToString = "Genre{id=1, name='Fiction'}";
        assertEquals(expectedToString, genre.toString());
    }
}