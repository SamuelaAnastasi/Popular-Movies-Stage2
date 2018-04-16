package com.example.android.popularmoviesstage2.ui;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmoviesstage2.R;
import com.example.android.popularmoviesstage2.data.Movie;
import com.example.android.popularmoviesstage2.interfaces.MoviesOnClickHandler;
import com.example.android.popularmoviesstage2.networking.NetworkUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This project is part of Android Developer Nanodegree Scholarship Program by
 * Udacity and Google
 *
 * The project is licensed under the MIT License(https://opensource.org/licenses/MIT)
 *
 * Copyright (c) 2018 - Samuela Anastasi
 */

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesHolder> {
    private static final String imageSize = "w500";

    private Cursor cursor;

    private MoviesOnClickHandler clickHandler;

    FavoritesAdapter (MoviesOnClickHandler clickHandler) {
        this.clickHandler = clickHandler;
    }

    @Override
    public FavoritesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_list_item_layout, parent, false);
        return new FavoritesHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoritesHolder holder, int position) {
        cursor.moveToPosition(position);
        holder.bindMovieData(holder.getCurrentMovie(cursor));
    }

    @Override
    public int getItemCount() {
        if (null == cursor) {
            return 0;
        }

        return cursor.getCount();
    }

    public class FavoritesHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.movie_poster)
        ImageView posterImageView;

        @BindView(R.id.tv_movie_title)
        TextView titleTextView;

        @BindView(R.id.tv_movie_genre)
        TextView genresTextView;

        FavoritesHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            cursor.moveToPosition(getAdapterPosition());
            Movie currentMovie = getCurrentMovie(cursor);
            clickHandler.onMovieClick(currentMovie);
        }

        private Movie getCurrentMovie(Cursor cursor) {
            Movie movie = new Movie();
            movie.setMovieId(cursor.getInt(MainActivity.INDEX_MOVIE_ID));
            movie.setMovieTitle(cursor.getString(MainActivity.INDEX_MOVIE_TITLE));
            movie.setOverview(cursor.getString(MainActivity.INDEX_MOVIE_OVERVIEW));
            movie.setVoteAverage(cursor.getFloat(MainActivity.INDEX_MOVIE_RATINGS));
            movie.setReleaseDate(cursor.getString(MainActivity.INDEX_MOVIE_RELEASE_DATE));
            movie.setGenreIds(cursor.getString(MainActivity.INDEX_MOVIE_GENRES));
            movie.setPosterPath(cursor.getString(MainActivity.INDEX_MOVIE_POSTER_PATH));
            movie.setBackdropPath(cursor.getString(MainActivity.INDEX_MOVIE_BACKDROP_PATH));

            return movie;
        }

        void bindMovieData(Movie currentMovie) {
            String imageUri = NetworkUtils.buildImageUrl(imageSize, currentMovie.getPosterPath());

            Picasso.get()
                    .load(imageUri)
                    .placeholder(R.drawable.placeholder_shape)
                    .error(R.drawable.error_shape)
                    .into(posterImageView);
            titleTextView.setText(currentMovie.getMovieTitle());
            genresTextView.setText(currentMovie.getGenres());
        }
    }

    public void swapCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }
        cursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}
