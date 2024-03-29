package com.example.ajisaputrars.madesubmission2.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.ajisaputrars.madesubmission2.BuildConfig;
import com.example.ajisaputrars.madesubmission2.Constant;
import com.example.ajisaputrars.madesubmission2.model.tvShow.TvShow;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TvShowViewModel extends ViewModel {
    private static final String URLFULL = Constant.URL_MOVIE_AND_TV_SHOW_BASE
            + Constant.URL_TV_SHOW_DISCOVER
            + "?api_key="
//            + Constant.API_KEY +
            + BuildConfig.API_KEY +
            "&language=en-US";

    private static final String URL_FILTERED_TV_SHOW = Constant.URL_MOVIE_AND_TV_SHOW_BASE
            + Constant.URL_TV_SHOW_SEARCH
            + "?api_key="
//            + Constant.API_KEY +
            + BuildConfig.API_KEY +
            "&language=en-US&query=";

    private MutableLiveData<ArrayList<TvShow>> listFilteredTvShows = new MutableLiveData<>();
    private MutableLiveData<ArrayList<TvShow>> listTvShows = new MutableLiveData<>();

    public void setListTvShows(final String cities) {
        final ArrayList<TvShow> listItems = new ArrayList<>();

        AsyncHttpClient client = new AsyncHttpClient();
        String url = URLFULL;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    Log.d("Trying TvShowVM", "Trying");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject tvShow = list.getJSONObject(i);
                        TvShow tvShowItem = new TvShow(tvShow);
                        listItems.add(tvShowItem);
                    }
                    listTvShows.postValue(listItems);

                } catch (Exception e) {
                    Log.d("Exception TvShowVM", e.getMessage());
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public void setTvShows(String searchQuery) {
        final ArrayList<TvShow> listItems = new ArrayList<>();

        AsyncHttpClient client = new AsyncHttpClient();
        String url = URL_FILTERED_TV_SHOW + searchQuery;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject tvShow = list.getJSONObject(i);
                        TvShow tvShowItem = new TvShow(tvShow);
                        listItems.add(tvShowItem);
                    }

                    listFilteredTvShows.postValue(listItems);

                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<TvShow>> getTvShows() {
        return listTvShows;
    }

    public LiveData<ArrayList<TvShow>> getFilteredTvShows() {
        return listFilteredTvShows;
    }
}
