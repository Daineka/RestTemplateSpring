package com.daineka.service.impl;

import com.daineka.repository.AuthorRepository;
import com.daineka.service.AuthorService;
import com.daineka.service.dto.AuthorDTO;
import com.daineka.service.dto.AuthorWithBooksDTO;
import com.daineka.service.mapper.AuthorMapper;
import com.daineka.service.mapper.AuthorWithBooksMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorMapper mapper;
    private final AuthorRepository authorRepository;
    private final AuthorWithBooksMapper authorWithBooksMapper;

    @Autowired
    public AuthorServiceImpl(AuthorMapper mapper, AuthorRepository authorRepository, AuthorWithBooksMapper authorWithBooksMapper) {
        this.mapper = mapper;
        this.authorRepository = authorRepository;
        this.authorWithBooksMapper = authorWithBooksMapper;
    }

    @Override
    public AuthorDTO saveOrUpdate(AuthorDTO authorDTO) {
        return mapper.toDto(authorRepository.saveAndFlush(mapper.toEntity(authorDTO)));
    }

    @Override
    public AuthorDTO get(Long id) {
        return mapper.toDto(authorRepository.getReferenceById(id));
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<AuthorDTO> getAll() {
        return authorRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public List<AuthorWithBooksDTO> getWithBooks() {
        return authorRepository.getWithBooks().stream().map(authorWithBooksMapper::toDto).toList();
    }
}
