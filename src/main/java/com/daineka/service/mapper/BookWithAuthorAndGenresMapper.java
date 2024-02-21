package com.daineka.service.mapper;

import com.daineka.entity.Book;
import com.daineka.service.dto.BookWithAuthorAndGenresDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {AuthorMapper.class, GenreMapper.class})
public interface BookWithAuthorAndGenresMapper {

    @Mapping(source = "author", target = "authorDTO")
    @Mapping(source = "genres", target = "genreDTOs")
    BookWithAuthorAndGenresDTO toDto(Book book);

    @Mapping(target = "author", source = "authorDTO")
    @Mapping(target = "genres", source = "genreDTOs")
    Book toEntity(BookWithAuthorAndGenresDTO bookWithAuthorAndGenresDTO);

}