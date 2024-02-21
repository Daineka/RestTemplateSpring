package com.daineka.service.impl;

import com.daineka.repository.GenreRepository;
import com.daineka.service.GenreService;
import com.daineka.service.dto.GenreDTO;
import com.daineka.service.mapper.GenreMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    private final GenreMapper mapper;
    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreMapper mapper, GenreRepository genreRepository) {
        this.mapper = mapper;
        this.genreRepository = genreRepository;
    }

    @Override
    public GenreDTO saveOrUpdate(GenreDTO genre) {
        return mapper.toDto(genreRepository.saveAndFlush(mapper.toEntity(genre)));
    }

    @Override
    public GenreDTO get(Long id) {
        return mapper.toDto(genreRepository.getReferenceById(id));
    }

    @Override
    public void delete(Long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public List<GenreDTO> getAll() {
        return genreRepository.findAll().stream().map(mapper::toDto).toList();
    }
}
