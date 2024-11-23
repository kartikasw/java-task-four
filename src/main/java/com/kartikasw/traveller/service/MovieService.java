package com.kartikasw.traveller.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.kartikasw.traveller.entity.Movie;
import com.kartikasw.traveller.exception.BusinessException;
import com.kartikasw.traveller.model.GeneralResponse;
import com.kartikasw.traveller.model.MetadataResponse;
import com.kartikasw.traveller.model.ResponseBuilder;
import com.kartikasw.traveller.util.ErrorMessage;

/// Buatlah sebuah class untuk menampung request parameter serta menyimpan request ke database!
/// NOTE: Bagian ketika menyimpan ke database cukup diberi remark saja.
@Service
public class MovieService implements IMovieService {

    private final List<Movie> movies = new ArrayList<>();

    @Override
    public GeneralResponse<MetadataResponse, Movie> addMovie(Movie movie) {
        validateMovie(movie);

        if (movies.stream().anyMatch(m -> m.getTitle().equalsIgnoreCase(movie.getTitle()))) {
            var error = String.format(ErrorMessage.ERROR_ALREADY_EXIST, movie.getTitle());
            throw new BusinessException(error);
        }

        movies.add(movie);

        return ResponseBuilder.responseBuilder(movie);
    }

    private void validateMovie(Movie movie) {
        if (Objects.isNull(movie)) {
            var error = String.format(ErrorMessage.ERROR_NULL_OBJECT, Movie.OBJECT_NAME);
            throw new BusinessException(error);
        }

        if (Objects.isNull(movie.getTitle() )) {
            var error = String.format(ErrorMessage.ERROR_MANDATORY_FIELD, Movie.TITLE_FIELD);
            throw new BusinessException(error);
        }

        // Buatlah sebuah validasi sederhana dimana properti rating tidak menerima nilai angka
        if (Objects.isNull(movie.getRating()) || movie.getRating().isEmpty()) {
            var error = String.format(ErrorMessage.ERROR_MANDATORY_FIELD, Movie.RATING_FIELD);
            throw new BusinessException(error);
        }
    }

}
