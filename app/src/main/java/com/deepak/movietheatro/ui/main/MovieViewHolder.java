package com.deepak.movietheatro.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.deepak.movietheatro.R;


/**
 * View holder of searched data
 * Created by deepak sachdeva on 14/08/17.
 *
 * version 1.0
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout linParent;
    public ImageView imgPoster;
    public TextView tvTitle;
    public TextView tvReleased;
    public TextView tvDirector;
    public TextView tvLanguage;
    public TextView tvCountry;

    MovieViewHolder(View view) {
        super(view);
        linParent = view.findViewById(R.id.lin_parent);
        imgPoster = view.findViewById(R.id.img_poster);
        tvTitle = view.findViewById(R.id.tv_title);
        tvReleased = view.findViewById(R.id.tv_released);
        tvDirector = view.findViewById(R.id.tv_director);
        tvLanguage = view.findViewById(R.id.tv_language);
        tvCountry = view.findViewById(R.id.tv_country);
    }
}
