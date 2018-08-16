package com.deepak.movietheatro.network;

import com.deepak.movietheatro.MovieTheatroApp;
import com.deepak.movietheatro.listeners.IResponseListener;
import com.deepak.movietheatro.models.MovieResponse;
import javax.annotation.ParametersAreNonnullByDefault;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkController {
    public void getMovie(IResponseListener iResponseListener, String searchText, String apiKey) {
        MovieTheatroApp.getRetrofitAPI().getMovie(searchText, apiKey).enqueue(new Callback<MovieResponse>() {
            @ParametersAreNonnullByDefault
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                iResponseListener.onResponse(response.body());
            }
            @ParametersAreNonnullByDefault
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                iResponseListener.onFailure(t);
            }
        });
    }

    public void getMovieDetails(IResponseListener iResponseListener, String imdbId, String apiKey) {
        MovieTheatroApp.getRetrofitAPI().getMovieDetails(imdbId, apiKey).enqueue(new Callback<MovieResponse>() {
            @ParametersAreNonnullByDefault
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                iResponseListener.onResponse(response.body());
            }
            @ParametersAreNonnullByDefault
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                iResponseListener.onFailure(t);
            }
        });
    }
}