package com.deepak.movietheatro.listeners;

import com.deepak.movietheatro.models.MovieResponse;

public interface IResponseListener {
    void onResponse(MovieResponse movieResponse);
    void onFailure(Throwable throwable);
}
