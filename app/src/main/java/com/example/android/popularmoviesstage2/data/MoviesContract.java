package com.example.android.popularmoviesstage2.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * This project is part of Android Developer Nanodegree Scholarship Program by
 * Udacity and Google
 *
 * The project is licensed under the MIT License(https://opensource.org/licenses/MIT)
 *
 * Copyright (c) 2018 - Samuela Anastasi
 */

public class MoviesContract {
    // Private empty constructor to avoid instantiation.
    private MoviesContract() {}

    // Using package name as authority
    static final String CONTENT_AUTHORITY = "com.example.android.popularmoviesstage2";

    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // Provider's path for favorite movies
    static final String PATH_MOVIES = "movies";

    // BaseColumns Entry class for (favorite) movies table
    public static final class MoviesEntry implements BaseColumns {

        // Content Uri to query the (favorite) movies table
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_MOVIES).build();

        // Used internally in the SQLite CREATE TABLE command
        static final String TABLE_NAME = "movies";

        // Column names (_ID column inherited by BaseColumns so it is not defined here)
        public static final String COLUMN_MOVIE_ID = "movieId";
        public static final String COLUMN_MOVIE_TITLE = "movieTitle";
        public static final String COLUMN_MOVIE_OVERVIEW = "overview";
        public static final String COLUMN_MOVIE_RATINGS = "rating";
        public static final String COLUMN_MOVIE_RELEASE_DATE = "releaseDate";
        public static final String COLUMN_MOVIE_GENRES = "genres";
        public static final String COLUMN_MOVIE_POSTER_PATH = "posterPath";
        public static final String COLUMN_MOVIE_BACKDROP_PATH = "backdropPath";
    }
}
