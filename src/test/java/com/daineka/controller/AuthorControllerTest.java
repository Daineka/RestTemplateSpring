package com.daineka.controller;

import com.daineka.exception_handling.NoSuchException;
import com.daineka.service.AuthorService;
import com.daineka.service.dto.AuthorDTO;
import com.daineka.service.dto.AuthorWithBooksDTO;
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
class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    @Test
    void showAll() {
        List<AuthorDTO> authors = new ArrayList<>();
        when(authorService.getAll()).thenReturn(authors);

        List<AuthorDTO> result = authorController.showAll();

        assertEquals(authors, result);
    }

    @Test
    void get_ValidId() {
        long authorId = 1L;
        AuthorDTO authorDTO = new AuthorDTO(authorId, "Test Author");
        when(authorService.get(authorId)).thenReturn(authorDTO);

        AuthorDTO result = authorController.get(authorId);

        assertEquals(authorDTO, result);
    }

    @Test
    void get_InvalidId() {
        long invalidId = 999L;
        when(authorService.get(invalidId)).thenReturn(null);

        assertThrows(NoSuchException.class, () -> authorController.get(invalidId));
    }

    @Test
    void getWithBooks() {
        List<AuthorWithBooksDTO> authorsWithBooks = new ArrayList<>();
        when(authorService.getWithBooks()).thenReturn(authorsWithBooks);

        List<AuthorWithBooksDTO> result = authorController.getWithBooks();

        assertEquals(authorsWithBooks, result);
    }

    @Test
    void add_ValidAuthorDTO() {
        AuthorDTO authorDTO = new AuthorDTO(1L, "Test Author");
        when(authorService.saveOrUpdate(authorDTO)).thenReturn(authorDTO);

        AuthorDTO result = authorController.add(authorDTO);

        assertEquals(authorDTO, result);
    }

    @Test
    void update_ExistingAuthorDTO() {
        AuthorDTO existingAuthorDTO = new AuthorDTO(1L, "Test Author");
        when(authorService.get(existingAuthorDTO.id())).thenReturn(existingAuthorDTO);
        when(authorService.saveOrUpdate(existingAuthorDTO)).thenReturn(existingAuthorDTO);

        AuthorDTO result = authorController.update(existingAuthorDTO);

        assertEquals(existingAuthorDTO, result);
    }

    @Test
    void update_NonExistingAuthorDTO() {
        AuthorDTO nonExistingAuthorDTO = new AuthorDTO(999L, "Non Existing Author");
        when(authorService.get(nonExistingAuthorDTO.id())).thenReturn(null);

        assertThrows(NoSuchException.class, () -> authorController.update(nonExistingAuthorDTO));
    }

    @Test
    void delete_ExistingAuthorId() {
        long existingAuthorId = 1L;
        when(authorService.get(existingAuthorId)).thenReturn(new AuthorDTO(existingAuthorId, "Test Author"));

        authorController.delete(existingAuthorId);

        verify(authorService, times(1)).delete(existingAuthorId);
    }

    @Test
    void delete_NonExistingAuthorId() {
        long nonExistingAuthorId = 999L;
        when(authorService.get(nonExistingAuthorId)).thenReturn(null);

        assertThrows(NoSuchException.class, () -> authorController.delete(nonExistingAuthorId));
    }
}