package com.example.ajisaputrars.madesubmission2;


import android.database.Cursor;

import com.example.ajisaputrars.madesubmission2.db.MovieDatabaseContract;
import com.example.ajisaputrars.madesubmission2.model.movie.Movie;

import java.util.ArrayList;

public class MappingHelper {
    public static ArrayList<Movie> mapCursorToArrayList(Cursor movieCursor) {
        ArrayList<Movie> moviesList = new ArrayList<>();

        while (movieCursor.moveToNext()) {
            int ID = movieCursor.getInt(movieCursor.getColumnIndexOrThrow(MovieDatabaseContract.MovieColumns.ID));
            String title = movieCursor.getString(movieCursor.getColumnIndexOrThrow(MovieDatabaseContract.MovieColumns.TITLE));
            String overview = movieCursor.getString(movieCursor.getColumnIndexOrThrow(MovieDatabaseContract.MovieColumns.OVERVIEW));
            String release_date = movieCursor.getString(movieCursor.getColumnIndexOrThrow(MovieDatabaseContract.MovieColumns.RELEASE_DATE));
            Double vote_average = movieCursor.getDouble(movieCursor.getColumnIndexOrThrow(MovieDatabaseContract.MovieColumns.VOTE_AVERAGE));
            String poster_path_string = movieCursor.getString(movieCursor.getColumnIndexOrThrow(MovieDatabaseContract.MovieColumns.POSTER_PATH_STRING));
            String backdrop_path_string = movieCursor.getString(movieCursor.getColumnIndexOrThrow(MovieDatabaseContract.MovieColumns.BACKDROP_PATH_STRING));

            Movie movie = new Movie();
            movie.setId(ID);
            movie.setTitle(title);
            movie.setOverview(overview);
            movie.setRelease_date(release_date);
            movie.setVote_average(vote_average);
            movie.setPoster_path_string(poster_path_string);
            movie.setBackdrop_path_string(backdrop_path_string);

            moviesList.add(movie);
        }

        return moviesList;
    }
}