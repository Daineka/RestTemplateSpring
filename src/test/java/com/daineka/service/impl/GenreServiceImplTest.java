package com.daineka.service.impl;

import com.daineka.entity.Genre;
import com.daineka.repository.GenreRepository;
import com.daineka.service.dto.GenreDTO;
import com.daineka.service.mapper.GenreMapper;
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
class GenreServiceImplTest {

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private GenreMapper genreMapper;

    @InjectMocks
    private GenreServiceImpl genreService;

    @Test
    void saveOrUpdate() {
        GenreDTO genreDTO = new GenreDTO(1L, "Fiction");
        Genre genre = new Genre(1L, "Fiction");
        when(genreMapper.toEntity(genreDTO)).thenReturn(genre);
        when(genreRepository.saveAndFlush(genre)).thenReturn(genre);
        when(genreMapper.toDto(genre)).thenReturn(genreDTO);

        GenreDTO savedGenreDTO = genreService.saveOrUpdate(genreDTO);

        assertEquals(genreDTO, savedGenreDTO);
        verify(genreMapper, times(1)).toEntity(genreDTO);
        verify(genreRepository, times(1)).saveAndFlush(genre);
        verify(genreMapper, times(1)).toDto(genre);
    }

    @Test
    void get() {
        Long genreId = 1L;
        Genre genre = new Genre(genreId, "Fiction");
        GenreDTO genreDTO = new GenreDTO(genreId, "Fiction");
        when(genreRepository.getReferenceById(genreId)).thenReturn(genre);
        when(genreMapper.toDto(genre)).thenReturn(genreDTO);

        GenreDTO retrievedGenreDTO = genreService.get(genreId);

        assertEquals(genreDTO, retrievedGenreDTO);
        verify(genreRepository, times(1)).getReferenceById(genreId);
        verify(genreMapper, times(1)).toDto(genre);
    }

    @Test
    void delete() {
        Long genreId = 1L;

        genreService.delete(genreId);

        verify(genreRepository, times(1)).deleteById(genreId);
    }

    @Test
    void getAll() {
        Genre genre = new Genre(1L, "Fiction");
        GenreDTO genreDTO = new GenreDTO(1L, "Fiction");
        when(genreRepository.findAll()).thenReturn(Collections.singletonList(genre));
        when(genreMapper.toDto(genre)).thenReturn(genreDTO);

        List<GenreDTO> genreDTOList = genreService.getAll();

        assertEquals(1, genreDTOList.size());
        assertEquals(genreDTO, genreDTOList.get(0));
        verify(genreRepository, times(1)).findAll();
        verify(genreMapper, times(1)).toDto(genre);
    }
}