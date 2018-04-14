package com.example.android.popularmoviesstage2.ui;

/**
 * This project is part of Android Developer Nanodegree Scholarship Program by
 * Udacity and Google
 *
 * The project is licenced under the MIT Licence(https://opensource.org/licenses/MIT)
 *
 * Copyright (c) 2018 - Samuela Anastasi
 */

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.android.popularmoviesstage2.R;
import com.example.android.popularmoviesstage2.data.Movie;
import com.example.android.popularmoviesstage2.data.MoviesContract;
import com.example.android.popularmoviesstage2.interfaces.MoviesFirstLoadAnimator;
import com.example.android.popularmoviesstage2.interfaces.MoviesOnClickHandler;
import com.example.android.popularmoviesstage2.tasks.FavoritesLoader;
import com.example.android.popularmoviesstage2.tasks.MoviesAsyncTask;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.android.popularmoviesstage2.networking.NetworkUtils.isConnected;

public class MainActivity extends AppCompatActivity implements MoviesOnClickHandler {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    // Bind resources
    @BindString(R.string.category_popular)
    String popular;

    @BindString(R.string.category_favorites)
    String favorites;

    @BindString(R.string.category_top_rated)
    String topRated;

    @BindString(R.string.search_option_popular)
    String searchOptionPopular;

    // Modified from working version added
    @BindString(R.string.sesearch_option_favorites)
    String searchOptionFavorites;

    @BindString(R.string.search_option_top_rated)
    String searchOptionTopRated;

    // Bind savedInstanceState Bundle key Strings
    @BindString(R.string.key_nav_option_selected)
    String keyNavItemSelected;

    @BindString(R.string.key_search_option_string)
    String keySearchOptionString;

    @BindString(R.string.key_action_bar_title)
    String keyActionBarTitle;

    @BindString(R.string.key_recycler_scroll_state)
    String recyclerScrollState;

    @BindColor(R.color.colorPrimary)
    int colorPrimary;

    @BindColor(R.color.colorAccent)
    int colorAccent;

    @BindColor(R.color.colorAccentFavorite)
    int toastBackgroundColor;

    // Bind views
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fl_recycler_container)
    FrameLayout recyclerFrame;

    @BindView(R.id.movies_recycler_view)
    GridRecyclerView rv;

    @BindView(R.id.no_internet_view)
    RelativeLayout noInternet;

    @BindView(R.id.no_favorites_view)
    LinearLayout noFavorites;

    @BindView(R.id.btn_try_again)
    Button buttonTryAgain;

    @BindView(R.id.bnv_main_navigation)
    BottomNavigationView navigationView;

    private MoviesAnimator moviesFirstLoadAnimator = new MoviesAnimator();
    private String searchOption;
    private ActionBar actionBar;

    NavigationItemSelectedListener navigationItemSelectedListener =
            new NavigationItemSelectedListener();

    NavigationItemReselectedListener navigationItemReselectedListener =
            new NavigationItemReselectedListener();

    GridLayoutManager gridLayoutManager;
    private Parcelable scrollState;

    public static final int ID_FAVORITES_LOADER = 11;
    FavoritesAdapter favoritesAdapter;

    // Cursor column to return from the db query
    public static final String[] FAVORITES_PROJECTION = {
            MoviesContract.MoviesEntry.COLUMN_MOVIE_ID,
            MoviesContract.MoviesEntry.COLUMN_MOVIE_TITLE,
            MoviesContract.MoviesEntry.COLUMN_MOVIE_OVERVIEW,
            MoviesContract.MoviesEntry.COLUMN_MOVIE_RATINGS,
            MoviesContract.MoviesEntry.COLUMN_MOVIE_RELEASE_DATE,
            MoviesContract.MoviesEntry.COLUMN_MOVIE_GENRES,
            MoviesContract.MoviesEntry.COLUMN_MOVIE_POSTER_PATH,
            MoviesContract.MoviesEntry.COLUMN_MOVIE_BACKDROP_PATH
    };

    // Cursor columns indices as defined in FAVORITES_PROJECTION (i.e. same order)
    public static final int INDEX_MOVIE_ID = 0;
    public static final int INDEX_MOVIE_TITLE = 1;
    public static final int INDEX_MOVIE_OVERVIEW = 2;
    public static final int INDEX_MOVIE_RATINGS = 3;
    public static final int INDEX_MOVIE_RELEASE_DATE = 4;
    public static final int INDEX_MOVIE_GENRES = 5;
    public static final int INDEX_MOVIE_POSTER_PATH = 6;
    public static final int INDEX_MOVIE_BACKDROP_PATH = 7;

    private static final String KEY_CURRENT_MOVIE = "currentMovie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        final int columnCount = getResources().getInteger(R.integer.movies_column_count);
        gridLayoutManager = new GridLayoutManager(this, columnCount);
        rv.setLayoutManager(gridLayoutManager);
        rv.setHasFixedSize(true);
        favoritesAdapter = new FavoritesAdapter(this);


        if (savedInstanceState == null) {
            navigationView.setSelectedItemId(R.id.action_popular);
            searchOption = searchOptionPopular;
            if(actionBar != null) {
                actionBar.setTitle(popular);
            }
            startMovieTask(this);
        } else {

            if(savedInstanceState.containsKey(keyNavItemSelected)) {
                navigationView.setSelectedItemId(savedInstanceState.getInt(keyNavItemSelected));
            }

            if(savedInstanceState.containsKey(keySearchOptionString)) {
                searchOption = savedInstanceState.getString(keySearchOptionString);
            }

            if(savedInstanceState.containsKey(keyActionBarTitle)) {
                if(actionBar != null) {
                    actionBar.setTitle(savedInstanceState.getString(keyActionBarTitle));
                }
            }

            startTaskPerSelectedOption(savedInstanceState.getInt(keyNavItemSelected));
        }

        // Set listeners for BottomNavigation item selected and reselected events
        navigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        navigationView.setOnNavigationItemReselectedListener(navigationItemReselectedListener);

        // Get layout params from BottomNavigationView
        CoordinatorLayout.LayoutParams layoutParams =
                (CoordinatorLayout.LayoutParams) navigationView.getLayoutParams();

        // Set custom {@link}BottomNavigationBehavior to show hide when scrolling
        layoutParams.setBehavior(new BottomNavigationBehavior());
        if (searchOption.equals(searchOptionFavorites)) {
            rv.setBackgroundColor(colorAccent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(keyNavItemSelected, navigationView.getSelectedItemId());
        outState.putString(keySearchOptionString, searchOption);

        if(actionBar != null) {
            if(actionBar.getTitle() instanceof String)
                outState.putString(keyActionBarTitle, actionBar.getTitle().toString());
        }

        scrollState = rv.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(recyclerScrollState, scrollState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null)
            scrollState = savedInstanceState.getParcelable(recyclerScrollState);
    }

    @Override
    public void onMovieClick(Movie movie) {
        Context context = MainActivity.this;
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(KEY_CURRENT_MOVIE, movie);
        context.startActivity(intent);
    }

    // Inner classes ------------------------------------------------------------------------------

    // Inner class implements MoviesFirstLoadAnimator interface
    private class MoviesAnimator implements MoviesFirstLoadAnimator {

        @Override
        public void onMoviesFirstLoad(List<Movie> movies) {
            if (movies != null) {
                MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this);
                movieAdapter.setMovies(movies);
                GridLayoutManager manager = (GridLayoutManager) rv.getLayoutManager();
                rv.setAdapter(movieAdapter);
                rv.setBackgroundColor(colorPrimary);
                if (scrollState != null) {
                    manager.onRestoreInstanceState(scrollState);
                }
                rv.scheduleLayoutAnimation();
            }
        }
    }

    // Inner class to handle BottomNavigationView item selection
    private class NavigationItemSelectedListener
            implements BottomNavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int itemId = item.getItemId();

            switch (itemId) {
                case R.id.action_popular:
                    searchOption = searchOptionPopular;
                    if(actionBar != null) {
                        actionBar.setTitle(popular);
                    }
                    startMovieTask(MainActivity.this);
                    return true;

                case R.id.action_favorites:
                    noInternet.setVisibility(View.GONE);
                    searchOption = searchOptionFavorites;
                    if(actionBar != null) {
                        actionBar.setTitle(favorites);
                    }
                    rv.setBackgroundColor(colorAccent);
                    startFavoritesLoader();
                    return true;
                case R.id.action_top_rated:
                    searchOption = searchOptionTopRated;
                    if(actionBar != null) {
                        actionBar.setTitle(topRated);
                    }
                    startMovieTask(MainActivity.this);
                    return true;
            }
            return false;
        }
    }

    // Inner class to handle BottomNavigationView item reselection
    // When bottom nav item is reselected the layout manager scrolls on the top of the list
    private class NavigationItemReselectedListener
            implements BottomNavigationView.OnNavigationItemReselectedListener {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem item) {
            if (isConnected(MainActivity.this) && rv.getVisibility() == View.GONE) {
                showRecyclerView();
                MoviesAsyncTask task = new MoviesAsyncTask(moviesFirstLoadAnimator);
                task.execute(searchOption);

            }
            ((GridLayoutManager) rv.getLayoutManager())
                    .scrollToPositionWithOffset(0, 0);
        }
    }

    // End of inner classes -----------------------------------------------------------------------

    // Create and execute AsyncTask to fetch savedMovies from API
    private void startMovieTask(Context context) {
        if (isConnected(context)) {
            showRecyclerView();
            MoviesAsyncTask task = new MoviesAsyncTask(moviesFirstLoadAnimator);
            task.execute(searchOption);
        } else {
            showErrorView();
        }
    }

    @OnClick(R.id.btn_try_again)
    public void tryAgain() {
        startMovieTask(this);
    }

    // Helper methods to toggle between error view and recycler view ------------------------------

    private void showRecyclerView() {
        noInternet.setVisibility(View.GONE);
        noFavorites.setVisibility(View.GONE);
        rv.setVisibility(View.VISIBLE);
    }

    private void showErrorView() {
        rv.setVisibility(View.GONE);
        noFavorites.setVisibility(View.GONE);
        noInternet.setVisibility(View.VISIBLE);
    }
    // End of methods to toggle between error view and recycler view ------------------------------

    // Start AsyncTask either for popular or top_rated
    private void startTaskPerSelectedOption(int option) {
        switch (option) {
            case R.id.action_popular:
                searchOption = searchOptionPopular;
                startMovieTask(this);
                break;
            case R.id.action_favorites:
                searchOption = searchOptionFavorites;
                startFavoritesLoader();
                break;

            case R.id.action_top_rated:
                searchOption = searchOptionTopRated;
                startMovieTask(this);
                break;
        }
    }

    private void startFavoritesLoader() {
        FavoritesAdapter favoritesAdapter = new FavoritesAdapter(MainActivity.this);
        getSupportLoaderManager().initLoader(
                ID_FAVORITES_LOADER, null, new FavoritesLoader(MainActivity.this,
                        favoritesAdapter));
        GridLayoutManager manager = (GridLayoutManager) rv.getLayoutManager();
        rv.setAdapter(favoritesAdapter);
        if (scrollState != null) {
            manager.onRestoreInstanceState(scrollState);
        }
        rv.scheduleLayoutAnimation();
    }
}



