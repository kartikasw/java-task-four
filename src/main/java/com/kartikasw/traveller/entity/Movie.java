package com.kartikasw.traveller.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Movie {
    public static final String OBJECT_NAME = "Movie";

    public static final String TITLE_FIELD = "Title";

    public static final String RATING_FIELD = "Rating";

    private String title;

    private String genre = "Unknown";

    private String duration = "-";

    private String director = "Unknown";

    private String rating;
}
