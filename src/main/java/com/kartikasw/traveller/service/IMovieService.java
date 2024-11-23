package com.kartikasw.traveller.service;

import com.kartikasw.traveller.entity.Movie;
import com.kartikasw.traveller.model.GeneralResponse;
import com.kartikasw.traveller.model.MetadataResponse;

public interface IMovieService {
    GeneralResponse<MetadataResponse, Movie> addMovie(Movie movie);
}
