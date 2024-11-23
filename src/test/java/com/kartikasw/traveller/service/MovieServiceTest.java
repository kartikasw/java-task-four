package com.kartikasw.traveller.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kartikasw.traveller.apps.movie.entity.Movie;
import com.kartikasw.traveller.apps.movie.service.MovieService;
import com.kartikasw.traveller.exception.BusinessException;
import com.kartikasw.traveller.util.ErrorMessage;

import static org.junit.jupiter.api.Assertions.*;

public class MovieServiceTest {
    private MovieService movieService;
    private Movie movie;

    @BeforeEach
    void setUp() {
        movieService = new MovieService();

        movie = new Movie();
        movie.setTitle("Inception");
        movie.setGenre("Action");
        movie.setDuration("2h");
        movie.setDirector("Christopher Nolan");
        movie.setRating("A+");
    }

    @Test
    void testAddMovie_Success() {
        var response = movieService.addMovie(movie);

        assertNotNull(response);
        assertEquals(movie, response.getData());
    }

    @Test
    void testAddMovie_DuplicateTitle_ShouldThrowException() {
        movieService.addMovie(movie);

        Movie duplicateMovie = movie;

        BusinessException thrown = assertThrows(BusinessException.class, () -> movieService.addMovie(duplicateMovie));
        assertEquals(String.format(ErrorMessage.ERROR_ALREADY_EXIST, duplicateMovie.getTitle()), thrown.getMessage());
    }

    @Test
    public void testAddMovie_InvalidRating() {
        movie.setRating("");

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            movieService.addMovie(movie);
        });

        assertEquals(String.format(ErrorMessage.ERROR_MANDATORY_FIELD, Movie.RATING_FIELD), exception.getMessage());
    }
}