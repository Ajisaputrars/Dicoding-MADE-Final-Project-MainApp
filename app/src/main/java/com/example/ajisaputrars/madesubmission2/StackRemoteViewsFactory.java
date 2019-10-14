package com.example.ajisaputrars.madesubmission2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.bumptech.glide.Glide;
import com.example.ajisaputrars.madesubmission2.db.MovieDatabaseContract;
import com.example.ajisaputrars.madesubmission2.model.movie.Movie;
import java.util.ArrayList;
import java.util.List;

class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
//    private final List<Bitmap> mWidgetItems = new ArrayList<>();
//    private final Context mContext;

    private final List<Bitmap> mWidgetItems = new ArrayList<>();
    private final Context mContext;
    private ArrayList<Movie> listMovies;
    Cursor cursor;

    StackRemoteViewsFactory(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        //required
    }

    @Override
    public void onDataSetChanged() {
        //Ini berfungsi untuk melakukan refresh saat terjadi perubahan.

        final long identityToken = Binder.clearCallingIdentity();

        cursor =  mContext.getContentResolver().query(
                MovieDatabaseContract.MovieColumns.CONTENT_URI,
                null, null, null, null);

        listMovies = MappingHelper.mapCursorToArrayList(cursor);

        Binder.restoreCallingIdentity(identityToken);

        for (Movie aMovie: listMovies) {
            try {

                Bitmap bitmap = Glide.with(mContext)
                        .asBitmap()
                        .load(aMovie.getPoster_path_string())
                        .submit(512, 512)
                        .get();

                mWidgetItems.add(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        //required
    }

    @Override
    public int getCount() {
        return mWidgetItems.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
        rv.setImageViewBitmap(R.id.imageView, mWidgetItems.get(position));

        Bundle extras = new Bundle();
        extras.putInt(ImagesBannerWidget.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

}
