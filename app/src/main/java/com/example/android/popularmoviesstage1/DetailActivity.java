package com.example.android.popularmoviesstage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class DetailActivity extends AppCompatActivity {
    private static final String API_BASE_URL = "http://api.themoviedb.org/";
    private Call<Trailer> call;
    private Call<Review> callReviews;
    private Trailer trailer;
    private Review review;
    private List<Trailer.TrailerItem> items;
    private List<Review.ReviewItem> reviews;
    private TrailerAdapter trailerAdapter;
    private ReviewAdapter reviewAdapter;
    private final String API_KEY = "36c9aef2c07c58b2a228ec4be25dadf8";
    private String id;
    private String version = "3";
    @BindView(R.id.detail_text)
    TextView textView;
    @BindView(R.id.plot_summary)
    TextView summary;
    @BindView(R.id.detail_activity_poster)
    ImageView imageView;
    @BindView(R.id.trailer_list)
    RecyclerView recyclerView;
    @BindView(R.id.review_list) RecyclerView reviewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("message") && intent.hasExtra("url")) {
            String message = intent.getStringExtra("message");
            String url = intent.getStringExtra("url");
            id = intent.getStringExtra("id");
            String plotSummary = intent.getStringExtra("plotsummary");
            Picasso.with(this).load(url).into(imageView);
            textView.setText(message);
            summary.setText(plotSummary);

        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        trailerAdapter = new TrailerAdapter(items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.setAdapter(trailerAdapter);

        reviewAdapter =  new ReviewAdapter(reviews);
        reviewList.setLayoutManager(new LinearLayoutManager(this));
        reviewList.setNestedScrollingEnabled(false);
        reviewList.setAdapter(reviewAdapter);

         MovieService.MovieApi movieApi = retrofit.create(MovieService.MovieApi.class);
        call = movieApi.getTrailers(version, id, API_KEY);
        call.enqueue(new Callback<Trailer>() {
            @Override
            public void onResponse(Call<Trailer> call, Response<Trailer> response) {
                trailer = response.body();
                items = trailer.getResults();
                trailerAdapter.swapList(items);
            }

            @Override
            public void onFailure(Call<Trailer> call, Throwable t) {
                Log.e("getQuestions threw: ", t.getMessage());
            }
        });

        callReviews = movieApi.getReviews(version,id,API_KEY);
        callReviews.enqueue(new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {
                review = response.body();
                reviews = review.getResults();
                reviewAdapter.swapList(reviews);
            }

            @Override
            public void onFailure(Call<Review> call, Throwable t) {
                Log.e("getQuestions threw: ", t.getMessage());

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
