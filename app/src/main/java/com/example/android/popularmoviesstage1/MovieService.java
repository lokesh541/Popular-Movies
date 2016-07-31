package com.example.android.popularmoviesstage1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lokesh on 24/7/16.
 */

public final class MovieService {
    public interface MovieApi {
        @GET("{version}/movie/top_rated?sort_by=popularity.desc&&api_key=36c9aef2c07c58b2a228ec4be25dadf8")
        Call<Movie> getMovies(
                @Path("version") String version

        );
    }
}
