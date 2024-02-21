package com.daineka.service.mapper;

import com.daineka.entity.Genre;
import com.daineka.service.dto.GenreDTO;
import org.mapstruct.Mapper;

@Mapper
public interface GenreMapper {

    Genre toEntity(GenreDTO genreDTO);

    GenreDTO toDto(Genre genre);
}