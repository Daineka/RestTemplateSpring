package com.daineka.service.dto;

import java.util.Set;

public record BookWithAuthorAndGenresDTO(Long id, String title, Integer publishedYear, AuthorDTO authorDTO,
                                         Set<GenreDTO> genreDTOs) {
}