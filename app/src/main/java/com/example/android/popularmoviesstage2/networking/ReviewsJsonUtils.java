package com.example.android.popularmoviesstage2.networking;

import android.text.TextUtils;
import android.util.Log;

import com.example.android.popularmoviesstage2.data.Review;

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

public class ReviewsJsonUtils {

    private static final String LOG_TAG = ReviewsJsonUtils.class.getSimpleName();

    // Error status key
    private static final String KEY_STATUS_CODE = "status_code";
    private static final String KEY_STATUS_MESSAGE = "status_message";

    //
    private static final String KEY_RESULTS_ALL = "results";

    // Reviews Json keys
    private static final String KEY_REVIEW_ID = "id";
    private static final String KEY_REVIEW_AUTHOR = "author";
    private static final String KEY_REVIEW_CONTENT = "content";
    private static final String KEY_REVIEW_URL = "url";

    // Private constructor to avoid instantiation. The class contains only static methods
    private ReviewsJsonUtils() {
    }

    public static ArrayList<Review> parseReviewsJsonString(String jsonString) {
        if (TextUtils.isEmpty(jsonString)) {
            return null;
        }
        ArrayList<Review> reviews = new ArrayList<>();

        try {

            JSONObject rootJsonObject = new JSONObject(jsonString);

            // Check if there is an error
            if (rootJsonObject.has(KEY_STATUS_CODE)) {
                Log.d(LOG_TAG, rootJsonObject.optString(KEY_STATUS_MESSAGE));
                return null;
            }

            JSONArray resultsReviewsArray = rootJsonObject.optJSONArray(KEY_RESULTS_ALL);
            if (resultsReviewsArray.length() > 0) {
                for (int i = 0; i < resultsReviewsArray.length(); i++) {
                    JSONObject currentReview = resultsReviewsArray.optJSONObject(i);

                    String reviewId = currentReview.optString(KEY_REVIEW_ID);
                    String reviewAuthor = currentReview.optString(KEY_REVIEW_AUTHOR);
                    String reviewContent = currentReview.optString(KEY_REVIEW_CONTENT);
                    String reviewUrl = currentReview.optString(KEY_REVIEW_URL);

                    Review review = new Review();

                    review.setReviewId(reviewId);
                    review.setReviewAuthor(reviewAuthor);
                    review.setReviewContent(reviewContent);
                    review.setReviewUrl(reviewUrl);

                    reviews.add(review);
                }
            } else {
                return null;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reviews;
    }
}
