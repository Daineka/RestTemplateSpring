package com.daineka.controller;

import com.daineka.exception_handling.NoSuchException;
import com.daineka.service.GenreService;
import com.daineka.service.dto.GenreDTO;
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
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<GenreDTO> showAll() {
        return genreService.getAll();
    }

    @GetMapping("/{id}")
    public GenreDTO get(@PathVariable Long id) {
        GenreDTO genre = genreService.get(id);
        if (genre == null) {
            String errorMsg = "Author not found with ID: " + id;
            throw new NoSuchException(errorMsg);
        }
        return genre;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenreDTO add(@RequestBody GenreDTO genre) {
        return genreService.saveOrUpdate(genre);
    }

    @PutMapping
    public GenreDTO update(@RequestBody GenreDTO genre) {
        GenreDTO existingGenre = genreService.get(genre.id());
        if (existingGenre == null) {
            throw new NoSuchException("Genre not found with ID: " + genre.id());
        }
        return genreService.saveOrUpdate(genre);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        GenreDTO genre = genreService.get(id);
        if (genre == null) {
            throw new NoSuchException("There is no genre with ID = " + id + " in Database");
        }
        genreService.delete(id);
    }
}
