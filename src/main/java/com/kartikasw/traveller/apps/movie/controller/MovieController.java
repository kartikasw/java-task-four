package com.kartikasw.traveller.apps.movie.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kartikasw.traveller.apps.movie.entity.Movie;
import com.kartikasw.traveller.apps.movie.model.MovieRequest;
import com.kartikasw.traveller.apps.movie.service.IMovieService;
import com.kartikasw.traveller.model.GeneralResponse;
import com.kartikasw.traveller.model.MetadataResponse;

@RestController
@Profile("movie")
@RequestMapping("/api/v1")
public class MovieController {

    private final IMovieService movieService;

    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping(value = "/addMovies")
    public ResponseEntity<GeneralResponse<MetadataResponse, Movie>> addMovie(@RequestBody MovieRequest request) {
        var result = movieService.addMovie(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
