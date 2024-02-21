package com.daineka.controller;

import com.daineka.exception_handling.NoSuchException;
import com.daineka.service.AuthorService;
import com.daineka.service.dto.AuthorDTO;
import com.daineka.service.dto.AuthorWithBooksDTO;
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
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorDTO> showAll() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public AuthorDTO get(@PathVariable Long id) {
        AuthorDTO author = authorService.get(id);
        if (author == null) {
            String errorMsg = "Author not found with ID: " + id;
            throw new NoSuchException(errorMsg);
        }
        return author;
    }

    @GetMapping("/withBooks")
    public List<AuthorWithBooksDTO> getWithBooks() {
        return authorService.getWithBooks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDTO add(@RequestBody AuthorDTO author) {
        return authorService.saveOrUpdate(author);
    }

    @PutMapping
    public AuthorDTO update(@RequestBody AuthorDTO author) {
        AuthorDTO existingAuthor = authorService.get(author.id());
        if (existingAuthor == null) {
            throw new NoSuchException("Author not found with ID: " + author.id());
        }
        return authorService.saveOrUpdate(author);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        AuthorDTO author = authorService.get(id);
        if (author == null) {
            throw new NoSuchException("Author not found with ID: " + id);
        }
        authorService.delete(id);
    }
}
