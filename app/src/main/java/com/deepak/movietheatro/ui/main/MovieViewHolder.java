package com.deepak.movietheatro.ui.main;

import android.support.v7.widget.RecyclerView;
import com.deepak.movietheatro.databinding.MovieListRowBinding;


/**
 * View holder of searched data
 * Created by deepak sachdeva on 14/08/17.
 *
 * version 1.0
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {

    public MovieListRowBinding movieListRowBinding;

    MovieViewHolder(MovieListRowBinding movieListRowBinding) {
        super(movieListRowBinding.getRoot());
        this.movieListRowBinding = movieListRowBinding;
    }
}
