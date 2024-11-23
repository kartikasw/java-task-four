package com.kartikasw.traveller.apps.movie.service;

import com.kartikasw.traveller.apps.movie.entity.Movie;
import com.kartikasw.traveller.apps.movie.model.MovieRequest;
import com.kartikasw.traveller.model.GeneralResponse;
import com.kartikasw.traveller.model.MetadataResponse;

public interface IMovieService {
    GeneralResponse<MetadataResponse, Movie> addMovie(MovieRequest movie);
}
