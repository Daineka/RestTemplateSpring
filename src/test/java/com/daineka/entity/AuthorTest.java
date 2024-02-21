package com.daineka.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AuthorTest {

    private Author author;

    @BeforeEach
    public void setUp() {
        author = new Author(1L, "John Doe");
    }

    @Test
    void testEqualsAndHashCode() {
        Author sameAuthor = new Author(1L, "John Doe");
        Author differentAuthor = new Author(2L, "Jane Smith");

        assertEquals(author, sameAuthor);
        assertEquals(author.hashCode(), sameAuthor.hashCode());

        assertNotEquals(author, differentAuthor);
        assertNotEquals(author.hashCode(), differentAuthor.hashCode());
    }

    @Test
    void testToString() {
        String expectedToString = "Author{id=1, name='John Doe'}";
        assertEquals(expectedToString, author.toString());
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(1L, author.getId());
        assertEquals("John Doe", author.getName());
        assertNotNull(author.getBooks());
    }

    @Test
    void testSetterAndGetters() {
        author.setId(2L);
        author.setName("Jane Smith");

        assertEquals(2L, author.getId());
        assertEquals("Jane Smith", author.getName());
    }
}