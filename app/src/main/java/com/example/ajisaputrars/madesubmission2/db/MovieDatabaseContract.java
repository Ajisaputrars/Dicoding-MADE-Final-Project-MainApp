package com.example.ajisaputrars.madesubmission2.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class MovieDatabaseContract {

    public static final String AUTHORITY = "com.example.ajisaputrars.madesubmission2";
    public static final String SCHEME = "content";

    public static final class MovieColumns implements BaseColumns {
        public static final String MOVIE_TABLE_NAME = "movie";

        static final String ID = "id";
        static final String TITLE = "title";
        static final String OVERVIEW = "overview";
        static final String RELEASE_DATE= "release_date";
        static final String VOTE_AVERAGE = "vote_average";
        static final String POSTER_PATH_STRING = "poster_path_string";
        static final String BACKDROP_PATH_STRING = "backdrop_path_string";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(MOVIE_TABLE_NAME)
                .build();
    }
}
