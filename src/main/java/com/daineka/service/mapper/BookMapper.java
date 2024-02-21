package com.daineka.service.mapper;

import com.daineka.entity.Book;
import com.daineka.service.dto.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BookMapper {

    @Mapping(target = "author.id", source = "authorId")
    Book toEntity(BookDTO bookDTO);

    @Mapping(source = "author.id", target = "authorId")
    BookDTO toDto(Book book);
}