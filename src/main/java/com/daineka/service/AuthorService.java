package com.daineka.service;

import com.daineka.service.dto.AuthorDTO;
import com.daineka.service.dto.AuthorWithBooksDTO;

import java.util.List;

public interface AuthorService extends Service<AuthorDTO> {
    List<AuthorWithBooksDTO> getWithBooks();
}