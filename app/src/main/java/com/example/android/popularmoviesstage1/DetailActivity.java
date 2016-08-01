package com.example.android.popularmoviesstage1;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.id.message;
import static java.security.AccessController.getContext;

public class DetailActivity extends AppCompatActivity {
    private MovieAdapter movieAdapter;

    @BindView(R.id.detail_text) TextView textView;
    @BindView(R.id.poster) ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("message")&&intent.hasExtra("url")) {
            String message = intent.getStringExtra("message");
            String url = intent.getStringExtra("url");
            Picasso.with(this).load(url).into(imageView);
                    textView.setText(message);
        }

    }
}
