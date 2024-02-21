package com.daineka.service;

import com.daineka.service.dto.BookDTO;
import com.daineka.service.dto.BookWithAuthorAndGenresDTO;

import java.util.List;

public interface BookService extends Service<BookDTO> {
    List<BookWithAuthorAndGenresDTO> getWithAuthorAndGenres();
}