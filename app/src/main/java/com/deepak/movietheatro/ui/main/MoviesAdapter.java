package com.deepak.movietheatro.ui.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.deepak.movietheatro.R;
import com.deepak.movietheatro.databinding.MovieListRowBinding;
import com.deepak.movietheatro.models.MovieResponse;
import com.deepak.movietheatro.ui.detail.MovieDetailActivity;
import com.deepak.movietheatro.utils.Constants;

/**
 * To display list of search data
 * Created by deepak sachdeva on 14/08/17.
 *
 * version 1.0
 */
public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> implements View.OnClickListener {

    private MovieResponse movieResponse;
    private Context context;
    private LayoutInflater layoutInflater;

    MoviesAdapter(Context context, MovieResponse movieResponse) {
        this.movieResponse = movieResponse;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        MovieListRowBinding movieListRowBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.movie_list_row, parent, false);

        return new MovieViewHolder(movieListRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        if (movieResponse != null) {
            holder.movieListRowBinding.setMovieResponse(movieResponse);
            holder.movieListRowBinding.linParent.setOnClickListener(this);
        }
    }

    @BindingAdapter({Constants.MOVIE_POSTER})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    @Override
    public int getItemCount() {
        if(movieResponse.getResponse().equalsIgnoreCase("True")){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(Constants.IMDB_ID, movieResponse.getImdbID());
        context.startActivity(intent);
    }
}