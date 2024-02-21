package com.daineka.controller;

import com.daineka.exception_handling.NoSuchException;
import com.daineka.service.BookService;
import com.daineka.service.dto.BookDTO;
import com.daineka.service.dto.BookWithAuthorAndGenresDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> showAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public BookDTO get(@PathVariable Long id) {
        BookDTO book = bookService.get(id);
        if (book == null) {
            String errorMsg = "Book not found with ID: " + id;
            throw new NoSuchException(errorMsg);
        }
        return book;
    }

    @GetMapping("/withAuthorAndGenres")
    public List<BookWithAuthorAndGenresDTO> getWithAuthorAndGenres() {
        return bookService.getWithAuthorAndGenres();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO add(@RequestBody BookDTO book) {
        return bookService.saveOrUpdate(book);
    }

    @PutMapping
    public BookDTO update(@RequestBody BookDTO book) {
        BookDTO existingBook = bookService.get(book.id());
        if (existingBook == null) {
            throw new NoSuchException("Book not found with ID: " + book.id());
        }
        return bookService.saveOrUpdate(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        BookDTO book = bookService.get(id);
        if (book == null) {
            String errorMsg = "Book not found with ID: " + id;
            throw new NoSuchException(errorMsg);
        }
        bookService.delete(id);
    }
}
