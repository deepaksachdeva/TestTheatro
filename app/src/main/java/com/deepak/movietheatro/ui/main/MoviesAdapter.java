package com.deepak.movietheatro.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.deepak.movietheatro.R;
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

    MoviesAdapter(Context context, MovieResponse movieResponse) {
        this.movieResponse = movieResponse;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);
        itemView.setOnClickListener(this);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        if (movieResponse != null) {
            holder.tvTitle.setText(movieResponse.getTitle());
            holder.tvReleased.setText(movieResponse.getReleased());
            holder.tvDirector.setText(movieResponse.getDirector());
            holder.tvLanguage.setText(movieResponse.getLanguage());
            holder.tvCountry.setText(movieResponse.getCountry());
            Glide.with(context)
                    .load(movieResponse.getPoster())
                    .thumbnail(0.5f)
                    .into(holder.imgPoster);
            holder.linParent.setOnClickListener(this);
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(Constants.IMDB_ID, movieResponse.getImdbID());
        context.startActivity(intent);
    }
}