package com.example.android.popularmoviesstage1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lokesh on 24/7/16.
 */

public final class MovieService {
    public interface MovieApi {
        @GET("{version}/movie/{sort_by}")
        Call<Movie> getMovies(
                @Path("version") String version,
                @Path("sort_by") String sort_by,
                @Query("api_key") String API_KEY

        );
    }
}
