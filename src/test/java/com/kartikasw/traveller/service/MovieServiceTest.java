package com.kartikasw.traveller.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.kartikasw.traveller.apps.movie.entity.Movie;
import com.kartikasw.traveller.apps.movie.model.MovieRequest;
import com.kartikasw.traveller.apps.movie.repository.MovieRepository;
import com.kartikasw.traveller.apps.movie.service.MovieService;
import com.kartikasw.traveller.exception.BusinessException;
import com.kartikasw.traveller.util.ErrorMessage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository repository;

    private MovieRequest movieRequest;
    private Movie movie;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        movieRequest = new MovieRequest();
        movieRequest.setTitle("Inception");
        movieRequest.setGenre("Action");
        movieRequest.setDuration("2h");
        movieRequest.setDirector("Christopher Nolan");
        movieRequest.setRating("A");

        movie = movieRequest.mapToEntity();
    }

    @Test
    void testAddMovie_Success() {
        when(repository.addMovie(any(Movie.class))).thenReturn(movie);

        var result = movieService.addMovie(movieRequest);

        assertNotNull(result);
        assertEquals(movie, result.getData());
        verify(repository, times(1)).addMovie(any(Movie.class));
    }

    @Test
    void testAddMovie_DuplicateTitle() {
        when(repository.addMovie(any(Movie.class)))
                .thenReturn(movie)
                .thenThrow(new BusinessException(String.format(ErrorMessage.ERROR_ALREADY_EXIST, movie.getTitle())));

        var firstResponse = movieService.addMovie(movieRequest);

        assertNotNull(firstResponse);
        assertEquals(movie, firstResponse.getData());

        var thrown = assertThrows(BusinessException.class, () -> movieService.addMovie(movieRequest));

        assertEquals(String.format(ErrorMessage.ERROR_ALREADY_EXIST, movie.getTitle()), thrown.getMessage());
        verify(repository, times(2)).addMovie(any(Movie.class));
    }

    @Test
    public void testAddMovie_EmptyRating() {
        movieRequest.setRating("");

        var thrown = assertThrows(BusinessException.class, () -> movieService.addMovie(movieRequest));

        assertEquals(String.format(ErrorMessage.ERROR_MANDATORY_FIELD, Movie.RATING_FIELD), thrown.getMessage());
        verify(repository, never()).addMovie(any(Movie.class));
    }

    @Test
    public void testAddMovie_InvalidRating() {
        movieRequest.setRating("-");

        var thrown = assertThrows(BusinessException.class, () -> movieService.addMovie(movieRequest));

        assertEquals(String.format(ErrorMessage.ERROR_INVALID_FORMAT, Movie.RATING_FIELD), thrown.getMessage());
        verify(repository, never()).addMovie(any(Movie.class));
    }
}