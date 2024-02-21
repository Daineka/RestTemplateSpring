package com.daineka.controller;

import com.daineka.exception_handling.NoSuchException;
import com.daineka.service.GenreService;
import com.daineka.service.dto.GenreDTO;
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
class GenreControllerTest {

    @Mock
    private GenreService genreService;

    @InjectMocks
    private GenreController genreController;

    @Test
    void showAll() {
        List<GenreDTO> genres = new ArrayList<>();
        when(genreService.getAll()).thenReturn(genres);

        List<GenreDTO> result = genreController.showAll();

        assertEquals(genres, result);
    }

    @Test
    void get_ValidId() {
        long genreId = 1L;
        GenreDTO genreDTO = new GenreDTO(genreId, "Test Genre");
        when(genreService.get(genreId)).thenReturn(genreDTO);

        GenreDTO result = genreController.get(genreId);

        assertEquals(genreDTO, result);
    }

    @Test
    void get_InvalidId() {
        long invalidId = 999L;
        when(genreService.get(invalidId)).thenReturn(null);

        assertThrows(NoSuchException.class, () -> genreController.get(invalidId));
    }

    @Test
    void add_ValidGenreDTO() {
        GenreDTO genreDTO = new GenreDTO(1L, "Test Genre");
        when(genreService.saveOrUpdate(genreDTO)).thenReturn(genreDTO);

        GenreDTO result = genreController.add(genreDTO);

        assertEquals(genreDTO, result);
    }

    @Test
    void update_ExistingGenreDTO() {
        GenreDTO existingGenreDTO = new GenreDTO(1L, "Test Genre");
        when(genreService.get(existingGenreDTO.id())).thenReturn(existingGenreDTO);
        when(genreService.saveOrUpdate(existingGenreDTO)).thenReturn(existingGenreDTO);

        GenreDTO result = genreController.update(existingGenreDTO);

        assertEquals(existingGenreDTO, result);
    }

    @Test
    void update_NonExistingGenreDTO() {
        GenreDTO nonExistingGenreDTO = new GenreDTO(999L, "Non Existing Genre");
        when(genreService.get(nonExistingGenreDTO.id())).thenReturn(null);

        assertThrows(NoSuchException.class, () -> genreController.update(nonExistingGenreDTO));
    }

    @Test
    void delete_ExistingGenreId() {
        long existingGenreId = 1L;
        when(genreService.get(existingGenreId)).thenReturn(new GenreDTO(existingGenreId, "Test Genre"));

        genreController.delete(existingGenreId);

        verify(genreService, times(1)).delete(existingGenreId);
    }

    @Test
    void delete_NonExistingGenreId() {
        long nonExistingGenreId = 999L;
        when(genreService.get(nonExistingGenreId)).thenReturn(null);

        assertThrows(NoSuchException.class, () -> genreController.delete(nonExistingGenreId));
    }
}