package com.example.android.popularmoviesstage2.data;

/**
 * This project is part of Android Developer Nanodegree Scholarship Program by
 * Udacity and Google
 *
 * The project is licensed under the MIT License(https://opensource.org/licenses/MIT)
 *
 * Copyright (c) 2018 - Samuela Anastasi
 */

// Trailer class holds main data, setters and getters for the trailer
public class Trailer {
    // Private fields
    private String trailerKey;
    private String trailerTitle;
    private String trailerSite;
    private String trailerType;

    // Default constructor
    public Trailer() {}

    // Getter methods ------------------------------------------------------------------------
    public String getTrailerKey() {
        return trailerKey;
    }

    public String getTrailerTitle() {
        return trailerTitle;
    }

    public String getTrailerSite() {
        return trailerSite;
    }

    public String getTrailerType() {
        return trailerType;
    }

    // Setter methods -----------------------------------------------------------------------
    public void setTrailerKey(String trailerKey) {
        this.trailerKey = trailerKey;
    }

    public void setTrailerTitle(String trailerTitle) {
        this.trailerTitle = trailerTitle;
    }

    public void setTrailerSite(String trailerSite) {
        this.trailerSite = trailerSite;
    }

    public void setTrailerType(String trailerType) {
        this.trailerType = trailerType;
    }
}
