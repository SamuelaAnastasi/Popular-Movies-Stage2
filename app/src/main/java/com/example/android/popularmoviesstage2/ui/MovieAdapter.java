package com.example.android.popularmoviesstage2.ui;

/**
 * This project is part of Android Developer Nanodegree Scholarship Program by
 * Udacity and Google
 *
 * The project is licenced under the MIT Licence(https://opensource.org/licenses/MIT)
 *
 * Copyright (c) 2018 - Samuela Anastasi
 */

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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private static final String LOG_TAG = MovieAdapter.class.getSimpleName();

    private static final String imageSize = "w500";

    private MoviesOnClickHandler clickHandler;

    // MovieAdapter data
    private List<Movie> movies;

    // Adapter constructor
    MovieAdapter(MoviesOnClickHandler clickHandler) {
        this.clickHandler = clickHandler;
    }

    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_list_item_layout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.MovieViewHolder holder, int position) {
        holder.setMovieData(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    // ViewHolder class declaration
    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.movie_poster)
        ImageView posterImageView;

        @BindView(R.id.tv_movie_title)
        TextView titleTextView;

        @BindView(R.id.tv_movie_genre)
        TextView genresTextView;

        Movie currentMovie;

        public MovieViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        public void setMovieData(Movie currentMovie) {
            this.currentMovie = currentMovie;
            String imageUri = NetworkUtils.buildImageUrl(imageSize, currentMovie.getPosterPath());

            Picasso.get()
                    .load(imageUri)
                    .placeholder(R.drawable.placeholder_shape)
                    .error(R.drawable.error_shape)
                    .into(posterImageView);
            titleTextView.setText(currentMovie.getMovieTitle());
            genresTextView.setText(currentMovie.getGenres());
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Movie movie = movies.get(adapterPosition);
            clickHandler.onMovieClick(movie);
        }
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        this.notifyDataSetChanged();
    }
}

