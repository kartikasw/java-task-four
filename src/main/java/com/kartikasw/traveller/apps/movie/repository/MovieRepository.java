package com.kartikasw.traveller.apps.movie.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kartikasw.traveller.apps.movie.entity.Movie;
import com.kartikasw.traveller.exception.BusinessException;
import com.kartikasw.traveller.util.ErrorMessage;

@Repository
public class MovieRepository {
    private final List<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie) {
        if (movies.stream().anyMatch(m -> m.getTitle().equalsIgnoreCase(movie.getTitle()))) {
            var error = String.format(ErrorMessage.ERROR_ALREADY_EXIST, movie.getTitle());
            throw new BusinessException(error);
        }

        movies.add(movie);
    }
}
