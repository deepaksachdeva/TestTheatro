package com.deepak.movietheatro.ui.detail;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.deepak.movietheatro.R;
import com.deepak.movietheatro.network.NetworkController;
import com.deepak.movietheatro.databinding.ActivityDetailBinding;
import com.deepak.movietheatro.listeners.IResponseListener;
import com.deepak.movietheatro.models.MovieResponse;
import com.deepak.movietheatro.utils.Constants;
import com.deepak.movietheatro.utils.NetworkUtils;

/**
 * To display detail of Movie
 * Created by deepak sachdeva on 15/08/17.
 *
 * version 1.0
 */
public class MovieDetailActivity extends AppCompatActivity implements IResponseListener {

    private ActivityDetailBinding activityDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailBinding = DataBindingUtil.setContentView(MovieDetailActivity.this,
                R.layout.activity_detail);
        activityDetailBinding.content.tvWebsite.setMovementMethod(LinkMovementMethod.getInstance());
        activityDetailBinding.toolbar.setTitle(Constants.MOVIE_DETAIL);
        setSupportActionBar(activityDetailBinding.toolbar);

        Bundle bundle = getIntent().getExtras();
        String imbdId = Constants.BLANK;
        if(bundle!= null) {
            imbdId = bundle.getString(Constants.IMDB_ID);
        }
        if(NetworkUtils.isNetworkConnected(getApplicationContext())) {
            NetworkController networkController = new NetworkController();
            // call Api to get detail of movie
            networkController.getMovieDetails(this, imbdId, Constants.API_KEY);
        }
    }

    /**
     * use to display image url coming from server using data-binding
     * @param view view
     * @param imageUrl url of image
     */
    @BindingAdapter({Constants.MOVIE_POSTER})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    @Override
    public void onResponse(MovieResponse movieResponse) {
        // bind response that can be used directly in xml fields
        activityDetailBinding.setMovieResponse(movieResponse);
    }

    @Override
    public void onFailure(Throwable throwable) {
        throwable.printStackTrace();
    }
}