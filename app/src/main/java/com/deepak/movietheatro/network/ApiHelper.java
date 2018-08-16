package com.deepak.movietheatro.network;

import com.deepak.movietheatro.models.MovieResponse;
import com.deepak.movietheatro.utils.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiHelper {

    @GET(Constants.SLASH)
    Call<MovieResponse> getMovie(@Query(Constants.T_TAG) String word, @Query(Constants.API_KEY_TAG) String apikey);

    @GET(Constants.SLASH)
    Call<MovieResponse> getMovieDetails(@Query(Constants.I_TAG) String imdbId, @Query(Constants.API_KEY_TAG) String apikey);
}