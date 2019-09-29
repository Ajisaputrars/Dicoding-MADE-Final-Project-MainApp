package com.example.ajisaputrars.madesubmission2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import com.example.ajisaputrars.madesubmission2.adapter.FilteredMovieAdapter;
import com.example.ajisaputrars.madesubmission2.model.movie.Movie;
import com.example.ajisaputrars.madesubmission2.viewmodel.MovieViewModel;
import java.util.ArrayList;

public class SearchMovieActivity extends AppCompatActivity {

    private MovieViewModel movieViewModel;
    private FilteredMovieAdapter adapter;
    private ArrayList<Movie> movies = new ArrayList<>();
    private SearchView searchView;

    private final String STATE_QUERY = "state_string";
    private String queryString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getFilteredMovies().observe(this, getMovie);

        RecyclerView rvFilteredMovies = findViewById(R.id.rv_fragment_search_movie);
        rvFilteredMovies.setHasFixedSize(true);
        rvFilteredMovies.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FilteredMovieAdapter(this);
        adapter.setMovies(movies);
        rvFilteredMovies.setAdapter(adapter);

        this.setTitle(R.string.search_movie);

        if (savedInstanceState != null) {
            queryString = savedInstanceState.getString(STATE_QUERY);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_QUERY, searchView.getQuery().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_movie, menu);

        MenuItem searchTvShowMenuItem = menu.findItem(R.id.action_search_movie);
        searchView = (SearchView) searchTvShowMenuItem.getActionView();

        searchView.onActionViewExpanded();

        if (queryString != null){
            searchView.setQuery(queryString, false);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                movieViewModel.setMovies(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    private Observer<ArrayList<Movie>> getMovie = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movieItems) {
            if (movieItems != null) {
                movies = movieItems;
                adapter.setData(movieItems);
            }
        }
    };
}
