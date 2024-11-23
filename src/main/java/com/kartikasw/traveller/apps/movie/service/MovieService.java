package com.kartikasw.traveller.apps.movie.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kartikasw.traveller.apps.movie.entity.Movie;
import com.kartikasw.traveller.apps.movie.model.MovieRequest;
import com.kartikasw.traveller.apps.movie.repository.MovieRepository;
import com.kartikasw.traveller.exception.BusinessException;
import com.kartikasw.traveller.model.GeneralResponse;
import com.kartikasw.traveller.model.MetadataResponse;
import com.kartikasw.traveller.model.ResponseBuilder;
import com.kartikasw.traveller.util.ErrorMessage;

/// Buatlah sebuah class untuk menampung request parameter serta menyimpan request ke database!
/// NOTE: Bagian ketika menyimpan ke database cukup diberi remark saja.
@Service
public class MovieService implements IMovieService {

    @Autowired
    private MovieRepository repository;

    @Override
    public GeneralResponse<MetadataResponse, Movie> addMovie(MovieRequest movie) {
        validateMovie(movie);
        var entity = movie.mapToEntity();
        repository.addMovie(entity);
        return ResponseBuilder.responseBuilder(entity);
    }

    private void validateMovie(MovieRequest movie) {
        if (Objects.isNull(movie)) {
            var error = String.format(ErrorMessage.ERROR_NULL_OBJECT, Movie.OBJECT_NAME);
            throw new BusinessException(error);
        }

        if (Objects.isNull(movie.getTitle())) {
            var error = String.format(ErrorMessage.ERROR_MANDATORY_FIELD, Movie.TITLE_FIELD);
            throw new BusinessException(error);
        }

        // Buatlah sebuah validasi sederhana dimana properti rating tidak menerima nilai
        // angka
        if (Objects.isNull(movie.getRating()) || movie.getRating().isEmpty()) {
            var error = String.format(ErrorMessage.ERROR_MANDATORY_FIELD, Movie.RATING_FIELD);
            throw new BusinessException(error);
        }
    }

}
