package com.example.android.popularmoviesstage2.ui;

/**
 * This project is part of Android Developer Nanodegree Scholarship Program by
 * Udacity and Google
 *
 * The project is licensed under the MIT License(https://opensource.org/licenses/MIT)
 *
 * Copyright (c) 2018 - Samuela Anastasi
 */

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcelable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmoviesstage2.R;
import com.example.android.popularmoviesstage2.data.Movie;
import com.example.android.popularmoviesstage2.data.MoviesContract;
import com.example.android.popularmoviesstage2.data.Review;
import com.example.android.popularmoviesstage2.data.Trailer;
import com.example.android.popularmoviesstage2.interfaces.ReviewsAsyncResultHandler;
import com.example.android.popularmoviesstage2.interfaces.TrailersAsyncResultHandler;
import com.example.android.popularmoviesstage2.networking.NetworkUtils;
import com.example.android.popularmoviesstage2.tasks.ReviewsAsyncTask;
import com.example.android.popularmoviesstage2.tasks.TrailersAsyncTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.example.android.popularmoviesstage2.data.MoviesContract.MoviesEntry;

import static com.example.android.popularmoviesstage2.networking.NetworkUtils.isConnected;

public class DetailsActivity extends AppCompatActivity implements
        TrailerAdapter.TrailerOnClickHandler, TrailersAsyncResultHandler,
        ReviewAdapter.ReviewOnClickHandler, ReviewsAsyncResultHandler {

    private static final String LOG_TAG = DetailsActivity.class.getSimpleName();

    // Bind views
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.backdrop_image)
    ImageView backdropImage;

    @BindView(R.id.movie_title)
    TextView movieTitle;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.tv_details_overview) TextView overview;

    @BindView(R.id.tv_release_date) TextView releaseDate;

    @BindView(R.id.tv_genres_content) TextView genres;

    @BindView(R.id.rb_rating_bar)
    RatingBar ratingBar;

    @BindView(R.id.details_coordinator)
    CoordinatorLayout detailsCoordinator;

    @BindView(R.id.rv_trailers)
    RecyclerView trailersRecycler;

    @BindView(R.id.container_trailers)
    RelativeLayout trailersContainer;

    @BindView(R.id.review_container)
    RelativeLayout reviewsContainer;

    @BindView(R.id.rv_reviews)
    RecyclerView reviewsRecycler;

    @BindView(R.id.nsv_movie_container)
    NestedScrollView nestedScrollView;

    // Bind resources
    @BindColor(R.color.fab_state_selector_normal)
    ColorStateList fabColorNormal;

    @BindColor(R.color.fab_state_selector_favorite)
    ColorStateList fabColorFavorite;

    @BindColor(R.color.colorAccentFavorite)
    int snackbarBackgroundColor;

    @BindColor(R.color.colorAccent)
    int colorAccent;

    @BindColor(R.color.primaryTextColor)
    int snackbarActionColor;

    @BindColor(R.color.colorPrimary)
    int colorPrimary;

    @BindString(R.string.details_toolbar_title)
    String detailsToolbarTitle;

    @BindString(R.string.key_intent_current_movie)
    String keyIntentCurrentMovie;

    @BindString(R.string.details_image_size)
    String detailsImageSize;

    @BindString(R.string.key_details_snackbar_dismissed)
    String keySnackBarDismissed;

    @BindString(R.string.snackbar_message)
    String snackBarMessage;

    @BindString(R.string.snackbar_button_label)
    String snackBarButtonLabel;

    @BindString(R.string.key_details_is_favorite)
    String keyIsFavorite;

    @BindString(R.string.removed_from_favorites)
    String removedFromFavorites;

    @BindString(R.string.added_to_favorites)
    String addedToFavorites;

    @BindString(R.string.key_recycler_scroll_state)
    String recyclerScrollState;

    @BindString(R.string.review_bundle_key)
    String reviewBundleKey;

    @BindString(R.string.nested_scroll_position)
    String nestedScrollPosition;

    @BindString(R.string.trailer_error_message)
    String trailerErrorMessage;

    @BindString(R.string.details_intent_error)
    String detailsIntentError;

    @BindString(R.string.package_manager_error)
    String packageManagerError;


    @BindString(R.string.review_error_message)
    String reviewErrorMessage;

    boolean isFavorite;
    boolean snackBarIsDismissed;
    int movieId;
    private Parcelable trailersScrollState;

    Movie currentMovie;
    String backdropUri;

    ActionBar actionBar;
    LinearLayoutManager trailersManager;
    TrailerAdapter trailerAdapter;
    ArrayList<Trailer> mTrailers = new ArrayList<>();
    ArrayList<Review> mReviews = new ArrayList<>();
    LinearLayoutManager reviewsManager;
    ReviewAdapter reviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(detailsToolbarTitle);

        Intent intent = getIntent();
        updateUi(intent);

        setTrailersRecycler();
        setReviewsRecycler();

        if (savedInstanceState != null) {
            requestTrailers();
            // On configuration changes skip querying db to check if movie is favorite
            if (savedInstanceState.containsKey(keyIsFavorite)) {
                isFavorite = savedInstanceState.getBoolean(keyIsFavorite);
                if (isFavorite) {
                    setFabColorFavorite();
                }
            }
            //If user already dismissed snackBar it will not show again on device rotation
            if (savedInstanceState.containsKey(keySnackBarDismissed)) {
                snackBarIsDismissed = savedInstanceState.getBoolean(keySnackBarDismissed);
            }

            if (savedInstanceState.containsKey(reviewBundleKey)) {
                mReviews = savedInstanceState.getParcelableArrayList(reviewBundleKey);
                if((mReviews != null) && mReviews.size() > 0) {
                    reviewAdapter.setReviews(mReviews);
                    reviewsRecycler.setAdapter(reviewAdapter);
                    reviewsContainer.setVisibility(View.VISIBLE);
                } else {
                    if (isConnected(DetailsActivity.this)) {
                        requestReviews();
                    }
                }
            }

        } else {
            snackBarIsDismissed = false;
            // if instance state == null query db to check if movie is favorite
            isFavorite = isMovieFavorite(currentMovie);
            if (isFavorite) {
                setFabColorFavorite();
            }
            if (!isConnected(DetailsActivity.this)) {
                trailersContainer.setVisibility(View.GONE);
                reviewsContainer.setVisibility(View.GONE);
            } else {
                requestTrailers();
                requestReviews();
            }
        }

        if (!isConnected(DetailsActivity.this)) {
            if (!snackBarIsDismissed) {
                showSnackBar();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(keyIsFavorite, isFavorite);
        outState.putBoolean(keySnackBarDismissed, snackBarIsDismissed);
        trailersScrollState = trailersRecycler.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(recyclerScrollState, trailersScrollState);
        outState.putParcelableArrayList(reviewBundleKey, mReviews);
        outState.putIntArray(nestedScrollPosition,
                new int[]{ nestedScrollView.getScrollX(), nestedScrollView.getScrollY()});
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null) {
            trailersScrollState = savedInstanceState.getParcelable(recyclerScrollState);
            final int[] position = savedInstanceState.getIntArray(nestedScrollPosition);
            if(position != null) {
                nestedScrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        nestedScrollView.smoothScrollTo(position[0], position[1]);
                    }
                });
            }
        }
    }

    @Override
    public void onTrailerClick(Trailer trailer) {
        String trailerYouTubeUrl = NetworkUtils
                .buildYouTubeVideoUrl(trailer.getTrailerKey());
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(trailerYouTubeUrl));
        PackageManager pm = getPackageManager();
        if (isConnected(this)) {
            if(intent.resolveActivity(pm) != null) {
                this.startActivity(intent);
            } else {
                showToast(packageManagerError);
                Log.e(LOG_TAG, detailsIntentError);
            }
        } else {
            showToast(trailerErrorMessage);
        }
    }

    @Override
    public void onReviewClick(Review review) {
        String reviewUrl = review.getReviewUrl();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(reviewUrl));
        PackageManager pm = getPackageManager();
        if (isConnected(this)) {
            if(intent.resolveActivity(pm) != null) {
                this.startActivity(intent);
            } else {
                showToast(packageManagerError);
                Log.e(LOG_TAG, detailsIntentError);
            }
        } else {
            showToast(reviewErrorMessage);
        }
    }

    @Override
    public void onReviewsAsyncResult(ArrayList<Review> reviews) {
        mReviews = reviews;
        if (reviews != null && reviews.size() > 0) {
            reviewsContainer.setVisibility(View.VISIBLE);
            ReviewAdapter adapter = (ReviewAdapter) reviewsRecycler.getAdapter();
            adapter.setReviews(reviews);
            reviewsRecycler.setAdapter(adapter);
        } else {
            reviewsContainer.setVisibility(View.GONE);
        }
    }

    @Override
    public void onTrailersAsyncResult(ArrayList<Trailer> trailers) {
        mTrailers = trailers;
        if (trailers != null && trailers.size() > 0) {
            trailersContainer.setVisibility(View.VISIBLE);
            TrailerAdapter adapter = (TrailerAdapter) trailersRecycler.getAdapter();
            adapter.setTrailers(trailers);
            LinearLayoutManager manager = (LinearLayoutManager) trailersRecycler.getLayoutManager();
            if (trailersScrollState != null) {
                manager.onRestoreInstanceState(trailersScrollState);
            }
            trailersRecycler.scheduleLayoutAnimation();
        } else {
            trailersContainer.setVisibility(View.GONE);
        }
    }

    private void updateUi(Intent intent) {
        if (intent != null && intent.hasExtra(keyIntentCurrentMovie)) {
            currentMovie = intent.getParcelableExtra(keyIntentCurrentMovie);
            movieId = (int) currentMovie.getMovieId();
            movieTitle.setText(currentMovie.getMovieTitle());
            overview.setText(currentMovie.getOverview());
            releaseDate.setText(currentMovie.getReleaseDate());
            genres.setText(currentMovie.getGenres());
            backdropUri = currentMovie.getBackdropPath();
            float ratingValue = currentMovie.getVoteAverage();
            ratingBar.setRating(ratingValue);
            loadBackdropImage();
        }
    }

    // Called inside onCreate() - instantiate the trailersManager and trailersAdapter
    // Creates and executes the AsyncTask to fetch mTrailers data from REST API
    private void requestTrailers() {
        String movieIdString = String.valueOf(movieId);
        TrailersAsyncTask trailersAsyncTask =
                new TrailersAsyncTask(DetailsActivity.this);
        trailersAsyncTask.execute(movieIdString);
    }

    // Called inside onCreate() - Creates and executes the AsyncTask to fetch mReviews data
    // from REST API - results will be handled by DetailsActivity (reviewsResultHandler)
    private void requestReviews() {
        String movieIdString = String.valueOf(movieId);
        ReviewsAsyncTask reviewsAsyncTask = new ReviewsAsyncTask(
                DetailsActivity.this);
        reviewsAsyncTask.execute(movieIdString);
    }

    @OnClick(R.id.fab)
    public void setIsFavorite() {
        if(isFavorite) {
            isFavorite = false;
            setFabColorNormal();
            int rowsDeleted = deleteFromFavorites(currentMovie);
            if (rowsDeleted > 0) {
                showToast(removedFromFavorites);
            }
        } else {
            isFavorite = true;
            setFabColorFavorite();
            Uri uri = insertToFavorites(currentMovie);
            if (uri != null) {
                showToast(addedToFavorites);
            }
        }
    }

    // DB CRUD operations private methods --------------------------------------------------------

    private Uri insertToFavorites(Movie movie) {
        ContentValues values = new ContentValues();
        int movieId = (int) movie.getMovieId();
        values.put(MoviesEntry.COLUMN_MOVIE_ID, movieId);
        values.put(MoviesEntry.COLUMN_MOVIE_TITLE, movie.getMovieTitle());
        values.put(MoviesEntry.COLUMN_MOVIE_OVERVIEW, movie.getOverview());
        values.put(MoviesEntry.COLUMN_MOVIE_RATINGS, movie.getVoteAverage());
        values.put(MoviesEntry.COLUMN_MOVIE_RELEASE_DATE, movie.getReleaseDate());
        values.put(MoviesEntry.COLUMN_MOVIE_GENRES, movie.getGenres());
        values.put(MoviesEntry.COLUMN_MOVIE_POSTER_PATH, movie.getPosterPath());
        values.put(MoviesEntry.COLUMN_MOVIE_BACKDROP_PATH, movie.getBackdropPath());
        // Insert to db through resolver
        return getContentResolver().insert(MoviesEntry.CONTENT_URI, values);
    }

    private int deleteFromFavorites(Movie movie) {
        int movieId = (int) movie.getMovieId();
        String selection = MoviesEntry.COLUMN_MOVIE_ID + "=?";
        String[] selectionArgs = new String[] {String.valueOf(movieId)};
        // delete movie through content resolver
        return getContentResolver().delete(MoviesEntry.CONTENT_URI, selection, selectionArgs);
    }

    private boolean isMovieFavorite(Movie movie) {
        Cursor cursor = getContentResolver().query(
                MoviesContract.MoviesEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int favoriteMovieId = cursor.getInt(
                        cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_MOVIE_ID));
                if (movieId == favoriteMovieId) {
                    return true;
                }
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return false;
    }

    // Handle FAB background color --------------------------------------------------------------
    private void setFabColorFavorite() {
        fab.setBackgroundTintList(fabColorFavorite);
    }

    private void setFabColorNormal() {
        fab.setBackgroundTintList(fabColorNormal);
    }

    // Custom Toast and SnackBar ----------------------------------------------------------------
    private void showToast (String message) {
        Toast toast = Toast.makeText(this, message,Toast.LENGTH_SHORT);
        View view = toast.getView();
        view.setBackgroundColor(snackbarBackgroundColor);
        view.setPadding(120,24,120,24);
        toast.show();
    }

    private void showSnackBar() {
        final Snackbar snackbar = Snackbar.make(detailsCoordinator,
                snackBarMessage,
                Snackbar.LENGTH_LONG);
        snackbar.setAction(snackBarButtonLabel, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackBarIsDismissed = true;
                snackbar.dismiss();
            }
        });
        snackbar.setActionTextColor(snackbarActionColor);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(snackbarBackgroundColor);
        TextView textView = snackBarView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setMinLines(3);
        textView.setMaxLines(3);
        snackbar.show();
    }

    private void loadBackdropImage() {
        String imageUri = NetworkUtils.buildImageUrl(detailsImageSize, backdropUri);
        Picasso.get()
                .load(imageUri)
                .placeholder(R.drawable.placeholder_shape_land)
                .error(R.drawable.error_shape_land)
                .into(backdropImage);
    }

    private void setTrailersRecycler() {
        trailersManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        trailersRecycler.setLayoutManager(trailersManager);
        trailersRecycler.setHasFixedSize(true);
        trailersRecycler.setNestedScrollingEnabled(false);
        trailerAdapter = new TrailerAdapter(this);
        trailerAdapter.setTrailers(mTrailers);
        trailersRecycler.setAdapter(trailerAdapter);
    }

    private void setReviewsRecycler() {
        reviewsManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        reviewsRecycler.setLayoutManager(reviewsManager);
        reviewsRecycler.setHasFixedSize(true);
        reviewsRecycler.setNestedScrollingEnabled(false);
        reviewAdapter = new ReviewAdapter(this);
        reviewAdapter.setReviews(mReviews);
        reviewsRecycler.setAdapter(reviewAdapter);
    }
}