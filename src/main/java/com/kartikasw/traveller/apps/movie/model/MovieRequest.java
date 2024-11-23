package com.kartikasw.traveller.apps.movie.model;

import com.kartikasw.traveller.apps.movie.entity.Movie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRequest {

    private String title;

    private String genre;

    private String duration;

    private String director;

    private String rating;

    public Movie mapToEntity() {
        Movie movie = new Movie();
        movie.setTitle(getTitle());
        movie.setGenre(getGenre());
        movie.setDuration(getDuration());
        movie.setDirector(getDirector());
        movie.setRating(getRating());

        return movie;
    }
}
