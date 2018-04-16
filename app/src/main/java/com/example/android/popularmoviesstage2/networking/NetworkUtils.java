package com.example.android.popularmoviesstage2.networking;

/**
 * This project is part of Android Developer Nanodegree Scholarship Program by
 * Udacity and Google
 *
 * The project is licensed under the MIT License(https://opensource.org/licenses/MIT)
 *
 * Copyright (c) 2018 - Samuela Anastasi
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;

import com.example.android.popularmoviesstage2.BuildConfig;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    private static final String API_KEY = BuildConfig.API_KEY;

    private static final String MOVIES_BASE_URL = "https://api.themoviedb.org/3/movie";
    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";
    private static final String VIDEO_BASE_URL = "https://www.youtube.com/watch?v=";
    private static final String VIDEO_IMAGE_BASE_URL = "https://img.youtube.com/vi/";

    private static final String VIDEO_IMAGE_SIZE = "mqdefault";
    private static final String VIDEO_IMAGE_TYPE = ".jpg";

    private static final String QUERY_PARAM_API_KEY = "api_key";
    private static final String QUERY_PARAM_LANGUAGE = "language";
    private static final String QUERY_PARAM_PAGE_LIMIT = "page";

    private static final String LANGUAGE_VALUE = "en-US";
    private static final String PAGE_LIMIT_VALUE = "1";

    private static final String PATH_TRAILER = "videos";
    private static final String PATH_REVIEWS = "reviews";

    // Private constructor to avoid instantiation. The class contains only static methods
    private NetworkUtils() {}

    // Methods for fetching the JSON response String --------------------------------------------

    public static String getMoviesJsonString(String navSelectedOption) throws IOException {

        URL moviesUrl = buildMoviesQueryUrl(navSelectedOption);

        return readInputStream(moviesUrl);
    }

    public static String getTrailersJsonString(String movieId)
            throws IOException {

        URL trailersUrl = buildTrailersQueryUrl(movieId);

        return readInputStream(trailersUrl);
    }

    public static String getReviewsJsonString(String movieId)
            throws IOException {
        URL reviewsUrl = buildReviewsQueryUrl(movieId);
        return readInputStream(reviewsUrl);
    }

    // Methods for building images and trailer Url Strings --------------------------------------

    /**
     * Build Url string for poster path or backdrop path
     * @param imageSizeString values are Poster: "w500" - Backdrop: "w780"
     * @param imagePath last segment of poster/backdrop path
     * @return path string for poster/backdrop
     */
    public static String buildImageUrl(String imageSizeString, String imagePath) {
        String imageUrl = IMAGE_BASE_URL + imageSizeString + imagePath;
        Log.v(LOG_TAG, imageUrl);
        return imageUrl;
    }

    public static String buildYouTubeVideoUrl(String key) {
        return VIDEO_BASE_URL + key;
    }

    public static String buildYouTubeImageUrl(String key) {
        return VIDEO_IMAGE_BASE_URL + key + "/" + VIDEO_IMAGE_SIZE + VIDEO_IMAGE_TYPE;
    }

    // Methods for building query URLs ----------------------------------------------------------

    /**
     * Build queryURL for movies by adding query parameters to the API
     * @param navSelectedOption dynamically chosen path String to append to the base Url String
     * @return final query URL for API endpoint
     */
    private static URL buildMoviesQueryUrl(String navSelectedOption) {

        Uri uri = Uri.parse(MOVIES_BASE_URL).buildUpon()
                .appendPath(navSelectedOption)
                .appendQueryParameter(QUERY_PARAM_API_KEY, API_KEY)
                .appendQueryParameter(QUERY_PARAM_LANGUAGE, LANGUAGE_VALUE)
                .appendQueryParameter(QUERY_PARAM_PAGE_LIMIT, PAGE_LIMIT_VALUE)
                .build();

        return createURL(uri.toString());
    }

    /**
     * Build query URL for trailers
     * @param movieIdString current movie's id
     * extracted value is long so when used parse it to String
     * @return query URL for trailers endpoints
     */
    private static URL buildTrailersQueryUrl(String movieIdString) {

        Uri uri = Uri.parse(MOVIES_BASE_URL).buildUpon()
                .appendPath(movieIdString)
                .appendPath(PATH_TRAILER)
                .appendQueryParameter(QUERY_PARAM_API_KEY, API_KEY)
                .build();

        return createURL(uri.toString());
    }

    /**
     * Build query URL for reviews
     * @param movieIdString movieIdString current movie's id
     * extracted value is long so when used parse it to String
     * @return query URL for reviews endpoints
     */
    private static URL buildReviewsQueryUrl(String movieIdString) {
        Uri uri = Uri.parse(MOVIES_BASE_URL).buildUpon()
                .appendPath(movieIdString)
                .appendPath(PATH_REVIEWS)
                .appendQueryParameter(QUERY_PARAM_API_KEY, API_KEY)
                .build();

        return createURL(uri.toString());
    }

    // ------------------------------------------------------------------------------------------

    /**
     * Create URL object from plain String
     * Method is called by {@link}buildMoviesQueryUrl
     * @param urlString query string with embedded query params
     * @return final query URL for API endpoint
     */
    private static URL createURL(String urlString) {
        URL url = null;
        try
        {
            if (!(urlString.isEmpty())) {
                url = new URL(urlString);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String readInputStream(URL url) throws IOException {
        if (url == null) {
            return null;
        }

        HttpURLConnection urlConnection = null;
        InputStream streamIn = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            streamIn = urlConnection.getInputStream();
            Scanner scanner = new Scanner(streamIn);
            scanner.useDelimiter("\\A");
            if (scanner.hasNext()) {
                return scanner.next();
            } else return null;

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (streamIn != null) {
                streamIn.close();
            }
        }
    }

    // Method used in MainActivity and DetailActivity to check the device connectivity
    public static boolean isConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnected();
        }
        return false;
    }
}
