package com.example.android.popularmoviesstage1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("http://goo.gl/gEgYUd"));
        movies.add(new Movie("http://goo.gl/gEgYUd"));
        movies.add(new Movie("http://goo.gl/gEgYUd"));
        movies.add(new Movie("http://goo.gl/gEgYUd"));
        GridView gridView = (GridView) findViewById(R.id.grid_view_movies);
        MovieAdapter movieAdapter = new MovieAdapter(this,movies);
        gridView.setAdapter(movieAdapter);


    }
}


