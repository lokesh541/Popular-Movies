package com.example.android.popularmoviesstage1;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by lokesh on 22/7/16.
 */

public class Movie {

    private String moviePosterUrl;

     public Movie(String posterUrl){
         moviePosterUrl = posterUrl;
     }


    public String getMoviePosterUrl(){
        return moviePosterUrl;
    }

}
