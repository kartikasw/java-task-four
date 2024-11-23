package com.kartikasw.traveller.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kartikasw.traveller.entity.Movie;
import com.kartikasw.traveller.model.GeneralResponse;
import com.kartikasw.traveller.model.MetadataResponse;
import com.kartikasw.traveller.service.IMovieService;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

    private final IMovieService movieService;

    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping(value = "/addMovies")
    public ResponseEntity<GeneralResponse<MetadataResponse, Movie>> addMovie(@RequestBody Movie movie) {
        var result = movieService.addMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
