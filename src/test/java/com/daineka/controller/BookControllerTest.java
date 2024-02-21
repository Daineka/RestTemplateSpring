package com.daineka.controller;

import com.daineka.exception_handling.NoSuchException;
import com.daineka.service.BookService;
import com.daineka.service.dto.BookDTO;
import com.daineka.service.dto.BookWithAuthorAndGenresDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    void showAll() {
        List<BookDTO> books = new ArrayList<>();
        when(bookService.getAll()).thenReturn(books);

        List<BookDTO> result = bookController.showAll();

        assertEquals(books, result);
    }

    @Test
    void get_ValidId() {
        long bookId = 1L;
        BookDTO bookDTO = new BookDTO(bookId, "Test Book", 2022, 1L);
        when(bookService.get(bookId)).thenReturn(bookDTO);

        BookDTO result = bookController.get(bookId);

        assertEquals(bookDTO, result);
    }

    @Test
    void get_InvalidId() {
        long invalidId = 999L;
        when(bookService.get(invalidId)).thenReturn(null);

        assertThrows(NoSuchException.class, () -> bookController.get(invalidId));
    }

    @Test
    void getWithAuthorAndGenres() {
        List<BookWithAuthorAndGenresDTO> booksWithAuthorAndGenres = new ArrayList<>();
        when(bookService.getWithAuthorAndGenres()).thenReturn(booksWithAuthorAndGenres);

        List<BookWithAuthorAndGenresDTO> result = bookController.getWithAuthorAndGenres();

        assertEquals(booksWithAuthorAndGenres, result);
    }

    @Test
    void add_ValidBookDTO() {
        BookDTO bookDTO = new BookDTO(1L, "Test Book", 2022, 1L);
        when(bookService.saveOrUpdate(bookDTO)).thenReturn(bookDTO);

        BookDTO result = bookController.add(bookDTO);

        assertEquals(bookDTO, result);
    }

    @Test
    void update_ExistingBookDTO() {
        BookDTO existingBookDTO = new BookDTO(1L, "Test Book", 2022, 1L);
        when(bookService.get(existingBookDTO.id())).thenReturn(existingBookDTO);
        when(bookService.saveOrUpdate(existingBookDTO)).thenReturn(existingBookDTO);

        BookDTO result = bookController.update(existingBookDTO);

        assertEquals(existingBookDTO, result);
    }

    @Test
    void update_NonExistingBookDTO() {
        BookDTO nonExistingBookDTO = new BookDTO(999L, "Non Existing Book", 2022, 1L);
        when(bookService.get(nonExistingBookDTO.id())).thenReturn(null);

        assertThrows(NoSuchException.class, () -> bookController.update(nonExistingBookDTO));
    }

    @Test
    void delete_ExistingBookId() {
        long existingBookId = 1L;
        when(bookService.get(existingBookId)).thenReturn(new BookDTO(existingBookId, "Test Book", 2022, 1L));

        bookController.delete(existingBookId);

        verify(bookService, times(1)).delete(existingBookId);
    }

    @Test
    void delete_NonExistingBookId() {
        long nonExistingBookId = 999L;
        when(bookService.get(nonExistingBookId)).thenReturn(null);

        assertThrows(NoSuchException.class, () -> bookController.delete(nonExistingBookId));
    }
}