package com.example.ajisaputrars.madesubmission2;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.ajisaputrars.madesubmission2.db.MovieDatabaseContract;
import com.example.ajisaputrars.madesubmission2.db.MovieHelper;

public class MovieProvider extends ContentProvider {

    private static final int NOTE = 1;
    private static final int NOTE_ID = 2;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private MovieHelper movieHelper;

    static {
        // content://com.dicoding.picodiploma.mynotesapp/note
        sUriMatcher.addURI(MovieDatabaseContract.AUTHORITY, MovieDatabaseContract.MovieColumns.MOVIE_TABLE_NAME, NOTE);
        // content://com.dicoding.picodiploma.mynotesapp/note/id
        sUriMatcher.addURI(MovieDatabaseContract.AUTHORITY, MovieDatabaseContract.MovieColumns.MOVIE_TABLE_NAME + "/#", NOTE_ID);
    }

    @Override
    public boolean onCreate() {
        movieHelper = MovieHelper.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        movieHelper.open();
        Cursor cursor;
        switch (sUriMatcher.match(uri)) {
            case NOTE:
                cursor = movieHelper.queryProvider();
                break;
            case NOTE_ID:
                cursor = movieHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }
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
//        noteHelper.open();
//        long added;
//        switch (sUriMatcher.match(uri)) {
//            case NOTE:
//                added = noteHelper.insertProvider(contentValues);
//                break;
//            default:
//                added = 0;
//                break;
//        }
//        getContext().getContentResolver().notifyChange(CONTENT_URI, new MainActivity.DataObserver(new Handler(), getContext()));
//        return Uri.parse(CONTENT_URI + "/" + added);
        return null;
    }
    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
