package com.example.android.popularmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.duration;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.os.Build.VERSION_CODES.M;
import static android.support.v7.appcompat.R.styleable.MenuItem;
import static com.example.android.popularmoviesstage1.MovieService.*;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";

    private static final String API_BASE_URL = "http://api.themoviedb.org/";
    private Call<Movie> call;
    private Movie movie;
    private List<Movie.MovieItem> items;
    private GridView gridView;
    private MovieAdapter movieAdapter;
    private String version = "3";
    private final String API_KEY = "36c9aef2c07c58b2a228ec4be25dadf8";
    private  String sort_by = "popular";

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.popular:
                sort_by = "popular";
                updateScreen(sort_by);
                item.setChecked(true);
                return true;
            case R.id.top_rated:
                sort_by = "top_rated";
                updateScreen(sort_by);
                item.setChecked(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateScreen(sort_by);

    }

public void updateScreen(String sort_by) {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    gridView = (GridView) findViewById(R.id.grid_view_movies);
    MovieApi movieApi = retrofit.create(MovieApi.class);
    call = movieApi.getMovies(version,sort_by,API_KEY);
    call.enqueue(new Callback<Movie>() {
        @Override
        public void onResponse(Call<Movie> call, Response<Movie> response) {

            try {
                movie = response.body();
                items = movie.getResults();
                movieAdapter = new MovieAdapter(MainActivity.this, items);
                gridView.setAdapter(movieAdapter);
                movieAdapter.swapList(items);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<Movie> call, Throwable t) {
            Log.e("getQuestions threw: ", t.getMessage());
        }
    });

    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            Context context = getApplicationContext();
            String title = movieAdapter.getItem(position).getTitle();
            String releaseDate = movieAdapter.getItem(position).getReleaseDate();
            String plotSummary = movieAdapter.getItem(position).getOverview();
            String message = title + "\n\nRelease date : " + releaseDate + "\n \nPlot Summary:\n" + plotSummary;
            Intent intent = new Intent(context, DetailActivity.class).
                    putExtra(Intent.EXTRA_TEXT, message);
            startActivity(intent);
        }
    });
}
}



