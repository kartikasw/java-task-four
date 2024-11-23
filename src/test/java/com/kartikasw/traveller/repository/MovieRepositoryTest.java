package com.kartikasw.traveller.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kartikasw.traveller.apps.movie.entity.Movie;
import com.kartikasw.traveller.apps.movie.repository.MovieRepository;
import com.kartikasw.traveller.exception.BusinessException;
import com.kartikasw.traveller.util.ErrorMessage;

public class MovieRepositoryTest {
    private MovieRepository movieRepository;

    private Movie movie;

    @BeforeEach
    void setUp() {
        movieRepository = new MovieRepository();

        movie = new Movie();
        movie.setTitle("Inception");
        movie.setGenre("Action");
        movie.setDuration("2h");
        movie.setDirector("Christopher Nolan");
        movie.setRating("A+");
    }

    @Test
    void testAddMovie_Success() {
        var result = movieRepository.addMovie(movie);
        assertNotNull(result);
        assertEquals(1, movieRepository.movies.size());
    }

    @Test
    void testAddMovie_DuplicateTitle() {
        movieRepository.addMovie(movie);

        var thrown = assertThrows(BusinessException.class, () -> {
            movieRepository.addMovie(movie);
        });

        assertEquals(String.format(ErrorMessage.ERROR_ALREADY_EXIST, movie.getTitle()), thrown.getMessage());
        assertEquals(1, movieRepository.movies.size());
    }
}
