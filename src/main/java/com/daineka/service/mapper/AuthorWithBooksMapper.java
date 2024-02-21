package com.daineka.service.mapper;

import com.daineka.entity.Author;
import com.daineka.service.dto.AuthorWithBooksDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {BookMapper.class})
public interface AuthorWithBooksMapper {

    @Mapping(source = "books", target = "booksDTO")
    AuthorWithBooksDTO toDto(Author author);

    @Mapping(target = "books", source = "booksDTO")
    Author toEntity(AuthorWithBooksDTO authorDTO);

}