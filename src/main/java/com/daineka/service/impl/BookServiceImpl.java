package com.daineka.service.impl;

import com.daineka.repository.BookRepository;
import com.daineka.service.BookService;
import com.daineka.service.dto.BookDTO;
import com.daineka.service.dto.BookWithAuthorAndGenresDTO;
import com.daineka.service.mapper.BookMapper;
import com.daineka.service.mapper.BookWithAuthorAndGenresMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookMapper mapper;
    private final BookRepository bookRepository;
    private final BookWithAuthorAndGenresMapper bookWithAuthorAndGenresMapper;

    @Autowired
    public BookServiceImpl(BookMapper mapper, BookRepository bookRepository, BookWithAuthorAndGenresMapper bookWithAuthorAndGenresMapper) {
        this.mapper = mapper;
        this.bookRepository = bookRepository;
        this.bookWithAuthorAndGenresMapper = bookWithAuthorAndGenresMapper;
    }

    @Override
    public BookDTO saveOrUpdate(BookDTO book) {
        return mapper.toDto(bookRepository.saveAndFlush(mapper.toEntity(book)));
    }

    @Override
    public BookDTO get(Long id) {
        return mapper.toDto(bookRepository.getReferenceById(id));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDTO> getAll() {
        return bookRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public List<BookWithAuthorAndGenresDTO> getWithAuthorAndGenres() {
        return bookRepository.getWithAuthorAndGenres().stream().map(bookWithAuthorAndGenresMapper::toDto).toList();
    }
}
