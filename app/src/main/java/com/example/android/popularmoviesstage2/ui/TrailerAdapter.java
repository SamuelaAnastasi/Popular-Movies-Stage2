package com.example.android.popularmoviesstage2.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmoviesstage2.R;
import com.example.android.popularmoviesstage2.data.Trailer;
import com.example.android.popularmoviesstage2.networking.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

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

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerHolder> {

    private List<Trailer> trailers;

    private final TrailerOnClickHandler trailerClickHandler;

    // Interface to handle clicks on RecyclerView items
    public interface TrailerOnClickHandler {
        void onTrailerClick(Trailer trailer);
    }

    // Adapter constructor
    TrailerAdapter(TrailerOnClickHandler trailerClickHandler) {
        this.trailerClickHandler = trailerClickHandler;
    }

    // Holder class definition
    public class TrailerHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        @BindView(R.id.iv_trailer_preview_image)
        ImageView trailerImageView;

        @BindView(R.id.tv_trailer_title)
        TextView trailerTitleTextView;

        @BindView(R.id.tv_trailer_type)
        TextView trailerTypeTextView;

        TrailerHolder( View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void bindTrailerData(Trailer currentTrailer) {

            String trailerPreviewImageUri = NetworkUtils.buildYouTubeImageUrl(
                    currentTrailer.getTrailerKey());
            Picasso.get()
                    .load(trailerPreviewImageUri)
                    .placeholder(R.drawable.ic_trailer_image_placeholder)
                    .error(R.drawable.ic_trailer_image_error)
                    .into(trailerImageView);

            trailerTitleTextView.setText(currentTrailer.getTrailerTitle());
            trailerTypeTextView.setText(currentTrailer.getTrailerType());
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Trailer trailer = trailers.get(adapterPosition);
            trailerClickHandler.onTrailerClick(trailer);
        }
    }

    @Override
    public TrailerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.details_trailer_layout,
                        parent, false);
        return new TrailerHolder(view);
    }

    @Override
    public void onBindViewHolder(TrailerAdapter.TrailerHolder holder, int position) {
        holder.bindTrailerData(trailers.get(position));
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        this.notifyDataSetChanged();
    }
}
