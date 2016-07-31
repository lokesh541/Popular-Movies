package com.example.android.popularmoviesstage1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lokesh on 22/7/16.
 */

public class MovieAdapter extends ArrayAdapter<Movie.MovieItem> {
    private List<Movie.MovieItem> items;


    public MovieAdapter(Context context, List<Movie.MovieItem> items) {
        super(context,0,items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View gridItemView = convertView;
        if (gridItemView == null) {
            gridItemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }

        Movie.MovieItem currentMovie = getItem(position);
        ImageView imageView = (ImageView) gridItemView.findViewById(R.id.movie_poster);
        String url = "http://image.tmdb.org/t/p/w500" + currentMovie.getPosterPath();
        Log.i("item url",url);

        Glide.with(getContext()).load(url).into(imageView);
        return gridItemView;
    }

    public int getCount() {
        if (items == null) {
            return -1;
        }
        return items.size();
    }

    public void swapList(List<Movie.MovieItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}

