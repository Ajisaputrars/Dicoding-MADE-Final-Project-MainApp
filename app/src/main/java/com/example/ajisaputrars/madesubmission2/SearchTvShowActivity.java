package com.example.ajisaputrars.madesubmission2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import com.example.ajisaputrars.madesubmission2.adapter.FilteredTvShowAdapter;
import com.example.ajisaputrars.madesubmission2.model.tvShow.TvShow;
import com.example.ajisaputrars.madesubmission2.viewmodel.TvShowViewModel;

import java.util.ArrayList;

public class SearchTvShowActivity extends AppCompatActivity {

    private TvShowViewModel tvShowViewModel;
    private FilteredTvShowAdapter adapter;
    private ArrayList<TvShow> tvShows = new ArrayList<>();

    private SearchView searchView;
    private final String STATE_QUERY = "state_string";
    private String queryString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tv_show);

        tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel.class);
        tvShowViewModel.getFilteredTvShows().observe(this, getTvShows);

        RecyclerView rvFilteredTvShow = findViewById(R.id.rv_fragment_search_tv_show);
        rvFilteredTvShow.setHasFixedSize(true);
        rvFilteredTvShow.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FilteredTvShowAdapter(this);
        adapter.setTvShows(tvShows);
        rvFilteredTvShow.setAdapter(adapter);

        this.setTitle(R.string.search_tv_show);

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
        getMenuInflater().inflate(R.menu.menu_search_tv_show, menu);

        MenuItem searchTvShowMenuItem = menu.findItem(R.id.action_search_tv_show);
        searchView = (SearchView) searchTvShowMenuItem.getActionView();

        searchView.onActionViewExpanded();

        if (queryString != null){
            searchView.setQuery(queryString, false);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                tvShowViewModel.setTvShows(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private Observer<ArrayList<TvShow>> getTvShows = new Observer<ArrayList<TvShow>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TvShow> tvShowsItem) {
            if (tvShowsItem != null) {
                tvShows = tvShowsItem;
                adapter.setData(tvShowsItem);
            }
        }
    };
}
