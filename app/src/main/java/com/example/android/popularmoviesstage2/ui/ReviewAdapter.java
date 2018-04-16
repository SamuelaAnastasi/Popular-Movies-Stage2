package com.example.android.popularmoviesstage2.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.popularmoviesstage2.R;
import com.example.android.popularmoviesstage2.data.Review;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by the_insider on 15/04/2018.
 */

public class ReviewAdapter  extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {
    private List<Review> reviews;

    private final ReviewOnClickHandler reviewClickHandler;

    // Interface to handle clicks on RecyclerView items
    public interface ReviewOnClickHandler {
        void onReviewClick(Review review);
    }

    // Adapter constructor
    ReviewAdapter(ReviewOnClickHandler reviewClickHandler) {
        this.reviewClickHandler = reviewClickHandler;
    }


    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.details_reviews_layout,
                    parent, false);
        return new ReviewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {
        holder.bindReviewData(reviews.get(position));
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ReviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.tv_author_name)
        TextView reviewAuthorTextView;

        @BindView(R.id.tv_review_content)
        TextView reviewContentTextView;

        public ReviewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void bindReviewData(Review currentReview) {

            reviewAuthorTextView.setText(currentReview.getReviewAuthor());
            reviewContentTextView.setText(currentReview.getReviewContent());
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Review review = reviews.get(adapterPosition);
            reviewClickHandler.onReviewClick(review);

        }
    }

    void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        this.notifyDataSetChanged();
    }
}
