package com.daineka.service.impl;

import com.daineka.entity.Author;
import com.daineka.entity.Book;
import com.daineka.repository.BookRepository;
import com.daineka.service.dto.BookDTO;
import com.daineka.service.dto.BookWithAuthorAndGenresDTO;
import com.daineka.service.mapper.BookMapper;
import com.daineka.service.mapper.BookWithAuthorAndGenresMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private BookWithAuthorAndGenresMapper bookWithAuthorAndGenresMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void saveOrUpdate() {
        BookDTO bookDTO = new BookDTO(1L, "Book", 2022, 1L);
        Book book = new Book(1L, "Book", 2022, new Author(1L, "Author"));
        when(bookMapper.toEntity(bookDTO)).thenReturn(book);
        when(bookRepository.saveAndFlush(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(bookDTO);

        BookDTO savedBookDTO = bookService.saveOrUpdate(bookDTO);

        assertEquals(bookDTO, savedBookDTO);
        verify(bookMapper, times(1)).toEntity(bookDTO);
        verify(bookRepository, times(1)).saveAndFlush(book);
        verify(bookMapper, times(1)).toDto(book);
    }

    @Test
    void get() {
        Long bookId = 1L;
        Book book = new Book(bookId, "Book", 2022, new Author(1L, "Author"));
        BookDTO bookDTO = new BookDTO(bookId, "Book", 2022, 1L);
        when(bookRepository.getReferenceById(bookId)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(bookDTO);

        BookDTO retrievedBookDTO = bookService.get(bookId);

        assertEquals(bookDTO, retrievedBookDTO);
        verify(bookRepository, times(1)).getReferenceById(bookId);
        verify(bookMapper, times(1)).toDto(book);
    }

    @Test
    void delete() {
        Long bookId = 1L;

        bookService.delete(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }

    @Test
    void getAll() {
        Book book = new Book(1L, "Book", 2022, new Author(1L, "Author"));
        BookDTO bookDTO = new BookDTO(1L, "Book", 2022, 1L);
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));
        when(bookMapper.toDto(book)).thenReturn(bookDTO);

        List<BookDTO> bookDTOList = bookService.getAll();

        assertEquals(1, bookDTOList.size());
        assertEquals(bookDTO, bookDTOList.get(0));
        verify(bookRepository, times(1)).findAll();
        verify(bookMapper, times(1)).toDto(book);
    }

    @Test
    void getWithAuthorAndGenres() {
        Book book = new Book(1L, "Book", 2022, new Author(1L, "Author"));
        BookWithAuthorAndGenresDTO bookWithAuthorAndGenresDTO = new BookWithAuthorAndGenresDTO(1L, "Book", 2022, null, Collections.emptySet());
        when(bookRepository.getWithAuthorAndGenres()).thenReturn(Collections.singletonList(book));
        when(bookWithAuthorAndGenresMapper.toDto(book)).thenReturn(bookWithAuthorAndGenresDTO);

        List<BookWithAuthorAndGenresDTO> bookWithAuthorAndGenresDTOList = bookService.getWithAuthorAndGenres();

        assertEquals(1, bookWithAuthorAndGenresDTOList.size());
        assertEquals(bookWithAuthorAndGenresDTO, bookWithAuthorAndGenresDTOList.get(0));
        verify(bookRepository, times(1)).getWithAuthorAndGenres();
    }
}