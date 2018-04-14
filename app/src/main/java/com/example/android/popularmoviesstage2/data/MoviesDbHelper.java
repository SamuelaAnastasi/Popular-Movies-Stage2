package com.example.android.popularmoviesstage2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.popularmoviesstage2.data.MoviesContract.MoviesEntry;

/**
 * This project is part of Android Developer Nanodegree Scholarship Program by
 * Udacity and Google
 *
 * The project is licenced under the MIT Licence(https://opensource.org/licenses/MIT)
 *
 * Copyright (c) 2018 - Samuela Anastasi
 */

public class MoviesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movies.db";

    private static final int DATABASE_VERSION = 1;

    MoviesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // SQLite CREATE TABLE command String
        final String SQL_CREATE_MOVIES_TABLE =
                "CREATE TABLE IF NOT EXISTS " + MoviesEntry.TABLE_NAME + " (" +
                        MoviesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        MoviesEntry.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +
                        MoviesEntry.COLUMN_MOVIE_TITLE + " TEXT NOT NULL, " +
                        MoviesEntry.COLUMN_MOVIE_OVERVIEW + " TEXT NOT NULL, " +
                        MoviesEntry.COLUMN_MOVIE_RATINGS + " REAL NOT NULL, " +
                        MoviesEntry.COLUMN_MOVIE_RELEASE_DATE + " TEXT NOT NULL, " +
                        MoviesEntry.COLUMN_MOVIE_GENRES + " TEXT NOT NULL, " +
                        MoviesEntry.COLUMN_MOVIE_POSTER_PATH + " TEXT NOT NULL, " +
                        MoviesEntry.COLUMN_MOVIE_BACKDROP_PATH + " TEXT NOT NULL, " +
                        " UNIQUE (" + MoviesEntry.COLUMN_MOVIE_ID + ") ON CONFLICT REPLACE);";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoviesEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
