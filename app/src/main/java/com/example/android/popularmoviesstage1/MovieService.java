package com.example.android.popularmoviesstage1;

import retrofit2.http.GET;

/**
 * Created by lokesh on 24/7/16.
 */

public final class MovieService {
  public interface MovieApi {
    @GET("(version)/discover/movie/");
    @PATH("version") String version;

  }
}
