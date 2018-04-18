package com.example.android.popularmoviesstage2.networking;

import android.text.TextUtils;
import android.util.Log;

import com.example.android.popularmoviesstage2.data.Trailer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This project is part of Android Developer Nanodegree Scholarship Program by
 * Udacity and Google
 *
 * The project is licensed under the MIT License(https://opensource.org/licenses/MIT)
 *
 * Copyright (c) 2018 - Samuela Anastasi
 */

public class TrailersJsonUtils {
    private static final String LOG_TAG = TrailersJsonUtils.class.getSimpleName();

    // Error status key
    private static final String KEY_STATUS_CODE = "status_code";
    private static final String KEY_STATUS_MESSAGE = "status_message";

    //
    private static final String KEY_RESULTS_ALL = "results";

    // Trailers Json keys
    private static final String KEY_YOUTUBE_ID = "key";
    private static final String KEY_TRAILER_NAME = "name";
    private static final String KEY_TRAILER_SITE = "site";
    private static final String KEY_TRAILER_TYPE = "type";

    private static final String VALUE_TRAILER_SITE = "YouTube";

    // Private constructor to avoid instantiation. The class contains only static methods
    private TrailersJsonUtils() {
    }

    public static ArrayList<Trailer> parseTrailersJsonString(String jsonString) {
        if (TextUtils.isEmpty(jsonString)) {
            return null;
        }
        ArrayList<Trailer> trailers = new ArrayList<>();

        try {

            JSONObject rootJsonObject = new JSONObject(jsonString);

            // Check if there is an error
            if (rootJsonObject.has(KEY_STATUS_CODE)) {
                Log.d(LOG_TAG, rootJsonObject.optString(KEY_STATUS_MESSAGE));
                return null;
            }

            JSONArray resultsTrailersArray = rootJsonObject.optJSONArray(KEY_RESULTS_ALL);
            if (resultsTrailersArray.length() > 0) {
                for (int i = 0; i < resultsTrailersArray.length(); i++) {
                    JSONObject currentTrailer = resultsTrailersArray.optJSONObject(i);

                    String trailerYouTubeKey = currentTrailer.optString(KEY_YOUTUBE_ID);
                    String trailerTitle = currentTrailer.optString(KEY_TRAILER_NAME);
                    String trailerSite = currentTrailer.optString(KEY_TRAILER_SITE);
                    String trailerType = currentTrailer.optString(KEY_TRAILER_TYPE);

                    Trailer trailer = new Trailer();
                    if (VALUE_TRAILER_SITE.equals(trailerSite)) {
                        // Set Movie instance fields
                        trailer.setTrailerKey(trailerYouTubeKey);
                        trailer.setTrailerTitle(trailerTitle);
                        trailer.setTrailerSite(trailerSite);
                        trailer.setTrailerType(trailerType);

                        // Add movie to List
                        trailers.add(trailer);
                    }
                }
            } else {
                return null;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return trailers;
    }
}

