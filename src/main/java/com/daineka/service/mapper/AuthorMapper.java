package com.daineka.service.mapper;

import com.daineka.entity.Author;
import com.daineka.service.dto.AuthorDTO;
import org.mapstruct.Mapper;

@Mapper
public interface AuthorMapper {

    Author toEntity(AuthorDTO authorDTO);

    AuthorDTO toDto(Author author);
}