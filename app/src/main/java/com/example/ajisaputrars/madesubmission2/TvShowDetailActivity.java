package com.example.ajisaputrars.madesubmission2;

import android.content.Intent;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ajisaputrars.madesubmission2.model.tvShow.TvShow;

public class TvShowDetailActivity extends AppCompatActivity {

    public static String DETAIL_TV_SHOW_EXTRA = "detail tv show extra";

    private TvShow tvShow;
    private TextView txtTitle;
    private TextView txtVoteAverage;
    private TextView txtDate;
    private TextView txtOverview;
    private ImageView imgPoster;

    private Menu menu;
    private boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);

        txtTitle = findViewById(R.id.text_detail_title_tv_show);
        txtVoteAverage = findViewById(R.id.text_detail_rating_tv_show);
        txtDate = findViewById(R.id.text_detail_calendar_tv_show);
        txtOverview = findViewById(R.id.text_detail_description_tv_show);
        imgPoster = findViewById(R.id.image_detail_tv_show);

        tvShow = getIntent().getParcelableExtra(DETAIL_TV_SHOW_EXTRA);
        setDetailMovieView();

        isFavorite = false;
    }

    private void setDetailMovieView() {
        if (tvShow != null){
            setTitle(R.string.title_detail_tv_show);
            txtTitle.setText(tvShow.getName());
            txtVoteAverage.setText(String.valueOf(tvShow.getVote_average()));
            txtDate.setText(tvShow.getFirst_air_date());
            txtOverview.setText(tvShow.getOverview());
            Glide.with(getApplicationContext()).load(tvShow.getPoster_path_string()).into(imgPoster);
        }
        else {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        this.menu = menu;

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_add_favorite_menu_detail) {
            isFavorite = !isFavorite;
            setFavorite();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setFavorite(){
        if (isFavorite) {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_added_to_favorites));
        } else {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_add_to_favorites));
        }
    }
}