package com.daineka.service.dto;

import java.util.Set;

public record AuthorWithBooksDTO(Long id, String name, Set<BookDTO> booksDTO) {
}