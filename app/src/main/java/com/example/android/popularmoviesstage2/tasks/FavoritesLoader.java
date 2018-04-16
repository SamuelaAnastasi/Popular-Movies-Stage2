package com.example.android.popularmoviesstage2.tasks;

import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.Loader;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.example.android.popularmoviesstage2.R;
import com.example.android.popularmoviesstage2.data.MoviesContract;
import com.example.android.popularmoviesstage2.ui.FavoritesAdapter;
import com.example.android.popularmoviesstage2.ui.GridRecyclerView;
import com.example.android.popularmoviesstage2.ui.MainActivity;
import static com.example.android.popularmoviesstage2.ui.MainActivity.ID_FAVORITES_LOADER;

/**
 * This project is part of Android Developer Nanodegree Scholarship Program by
 * Udacity and Google
 *
 * The project is licensed under the MIT License(https://opensource.org/licenses/MIT)
 *
 * Copyright (c) 2018 - Samuela Anastasi
 */

public class FavoritesLoader implements LoaderManager.LoaderCallbacks<Cursor>{
    private MainActivity context;
    private FavoritesAdapter favoritesAdapter;

    public FavoritesLoader(MainActivity context, FavoritesAdapter favoritesAdapter) {
        this.context = context;
        this.favoritesAdapter = favoritesAdapter;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case ID_FAVORITES_LOADER:
                Uri favoritesQueryUri = MoviesContract.MoviesEntry.CONTENT_URI;
                String[] projection = MainActivity.FAVORITES_PROJECTION;
                return new CursorLoader(context,
                        favoritesQueryUri,
                        projection,
                        null,
                        null,
                        null);
            default:
                throw new RuntimeException(context.getString(R.string.loader_error) + id);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        favoritesAdapter.swapCursor(cursor);
        if(cursor.getCount() != 0) {
            showFavorites(context);
        } else {
            showNoFavorites(context);
        }
    }
    private void showFavorites(MainActivity context) {
        GridRecyclerView rv = context.findViewById(R.id.movies_recycler_view);
        LinearLayout ll = context.findViewById(R.id.no_favorites_view);
        ll.setVisibility(View.GONE);
        rv.setVisibility(View.VISIBLE);
    }

    private void showNoFavorites(MainActivity context) {
        GridRecyclerView rv = context.findViewById(R.id.movies_recycler_view);
        LinearLayout ll = context.findViewById(R.id.no_favorites_view);
        rv.setVisibility(View.GONE);
        ll.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        favoritesAdapter.swapCursor(null);
    }
}
