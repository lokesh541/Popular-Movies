package com.example.android.popularmoviesstage1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lokesh on 22/7/16.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {

    public MovieAdapter(Context context,ArrayList<Movie> movies) {
        super(context, 0, movies);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

         View gridItemView = convertView;
         if (gridItemView == null){
                gridItemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item,parent,false);
         }

        Movie currentMovie  = getItem(position);
        ImageView imageView = (ImageView) gridItemView.findViewById(R.id.movie_poster);
        Picasso.with(getContext()).load("http://goo.gl/gEgYUd").into(imageView);

        //Glide.with(getContext()).load("http://goo.gl/gEgYUd").into(poster);
        return gridItemView;
    }
}
