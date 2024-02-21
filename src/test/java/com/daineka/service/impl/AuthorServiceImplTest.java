package com.daineka.service.impl;

import com.daineka.entity.Author;
import com.daineka.repository.AuthorRepository;
import com.daineka.service.dto.AuthorDTO;
import com.daineka.service.dto.AuthorWithBooksDTO;
import com.daineka.service.mapper.AuthorMapper;
import com.daineka.service.mapper.AuthorWithBooksMapper;
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
class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapper authorMapper;

    @Mock
    private AuthorWithBooksMapper authorWithBooksMapper;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    void saveOrUpdate() {
        AuthorDTO authorDTO = new AuthorDTO(1L, "Author");
        Author author = new Author(1L, "Author");
        when(authorMapper.toEntity(authorDTO)).thenReturn(author);
        when(authorRepository.saveAndFlush(author)).thenReturn(author);
        when(authorMapper.toDto(author)).thenReturn(authorDTO);

        AuthorDTO savedAuthorDTO = authorService.saveOrUpdate(authorDTO);

        assertEquals(authorDTO, savedAuthorDTO);
        verify(authorMapper, times(1)).toEntity(authorDTO);
        verify(authorRepository, times(1)).saveAndFlush(author);
        verify(authorMapper, times(1)).toDto(author);
    }

    @Test
    void get() {
        Long authorId = 1L;
        Author author = new Author(authorId, "Author");
        AuthorDTO authorDTO = new AuthorDTO(authorId, "Author");
        when(authorRepository.getReferenceById(authorId)).thenReturn(author);
        when(authorMapper.toDto(author)).thenReturn(authorDTO);

        AuthorDTO retrievedAuthorDTO = authorService.get(authorId);

        assertEquals(authorDTO, retrievedAuthorDTO);
        verify(authorRepository, times(1)).getReferenceById(authorId);
        verify(authorMapper, times(1)).toDto(author);
    }

    @Test
    void delete() {
        Long authorId = 1L;

        authorService.delete(authorId);

        verify(authorRepository, times(1)).deleteById(authorId);
    }

    @Test
    void getAll() {
        Author author = new Author(1L, "Author");
        AuthorDTO authorDTO = new AuthorDTO(1L, "Author");
        when(authorRepository.findAll()).thenReturn(Collections.singletonList(author));
        when(authorMapper.toDto(author)).thenReturn(authorDTO);

        List<AuthorDTO> authorDTOList = authorService.getAll();

        assertEquals(1, authorDTOList.size());
        assertEquals(authorDTO, authorDTOList.get(0));
        verify(authorRepository, times(1)).findAll();
        verify(authorMapper, times(1)).toDto(author);
    }

    @Test
    void getWithBooks() {
        Author author = new Author(1L, "Author");
        AuthorWithBooksDTO authorWithBooksDTO = new AuthorWithBooksDTO(1L, "Author", Collections.emptySet());
        when(authorRepository.getWithBooks()).thenReturn(Collections.singletonList(author));
        when(authorWithBooksMapper.toDto(author)).thenReturn(authorWithBooksDTO);

        List<AuthorWithBooksDTO> authorWithBooksDTOList = authorService.getWithBooks();

        assertEquals(1, authorWithBooksDTOList.size());
        assertEquals(authorWithBooksDTO, authorWithBooksDTOList.get(0));
        verify(authorRepository, times(1)).getWithBooks();
        verify(authorWithBooksMapper, times(1)).toDto(author);
    }
}