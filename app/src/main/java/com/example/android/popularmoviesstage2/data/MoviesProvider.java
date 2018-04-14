package com.example.android.popularmoviesstage2.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.android.popularmoviesstage2.data.MoviesContract.MoviesEntry;

/**
 * This project is part of Android Developer Nanodegree Scholarship Program by
 * Udacity and Google
 *
 * The project is licenced under the MIT Licence(https://opensource.org/licenses/MIT)
 *
 * Copyright (c) 2018 - Samuela Anastasi
 */
public class MoviesProvider extends ContentProvider {

    public static final String LOG_TAG = MoviesProvider.class.getSimpleName();

    public static final int CODE_MOVIES = 100;
    public static final int CODE_MOVIE_WITH_ID = 101;

    private MoviesDbHelper moviesDbHelper;
    private static final UriMatcher uriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = MoviesContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, MoviesContract.PATH_MOVIES, CODE_MOVIES);
        matcher.addURI(authority, MoviesContract.PATH_MOVIES + "/#", CODE_MOVIE_WITH_ID);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        moviesDbHelper = new MoviesDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri,
                        @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {

        SQLiteDatabase database = moviesDbHelper.getReadableDatabase();

        Cursor cursor;
        int match = uriMatcher.match(uri);
        switch (match) {
            case CODE_MOVIES:
                cursor = database.query(MoviesEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            case CODE_MOVIE_WITH_ID:
                cursor = database.query(MoviesEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            default:
                database.close();
                throw new IllegalArgumentException("Unknown Uri " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase database = moviesDbHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        switch (match) {
            case CODE_MOVIES:
                return insertMovie(database, uri, contentValues);
            default:
                database.close();
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {

        SQLiteDatabase database = moviesDbHelper.getWritableDatabase();
        int rowsDeleted;
        int match = uriMatcher.match(uri);
        switch (match) {
            case CODE_MOVIES:
                rowsDeleted = database.delete(MoviesEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case CODE_MOVIE_WITH_ID:
                rowsDeleted = database.delete(MoviesEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                database.close();
                throw new IllegalArgumentException("Deletion not supported for uri: " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues,
                      @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    // Helper method for insert()
    private Uri insertMovie(SQLiteDatabase database,Uri uri, ContentValues values) {
        String posterPath = values.getAsString(MoviesEntry.COLUMN_MOVIE_POSTER_PATH);
        if(posterPath == null) {
            throw new IllegalArgumentException("Poster path should not be null");
        }
        String backdropPath = values.getAsString(MoviesEntry.COLUMN_MOVIE_BACKDROP_PATH);
        if(backdropPath == null) {
            throw new IllegalArgumentException("Backdrop path should not be null");
        }

        database = moviesDbHelper.getWritableDatabase();
        long rowId = database.insert(MoviesEntry.TABLE_NAME, null, values);
        if (rowId == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, rowId);
    }
}
