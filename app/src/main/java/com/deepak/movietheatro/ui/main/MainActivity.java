package com.deepak.movietheatro.ui.main;

import android.app.SearchManager;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.deepak.movietheatro.R;
import com.deepak.movietheatro.databinding.ActivityMainBinding;
import com.deepak.movietheatro.network.NetworkController;
import com.deepak.movietheatro.listeners.IResponseListener;
import com.deepak.movietheatro.models.MovieResponse;
import com.deepak.movietheatro.utils.Constants;
import com.deepak.movietheatro.utils.NetworkUtils;

/**
 * MainActivity to search movie from search option
 * Created by deepak sachdeva on 14/08/17.
 * <p>
 * version 1.0
 */

public class MainActivity extends AppCompatActivity implements IResponseListener {
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(MainActivity.this,
                R.layout.activity_main);

        activityMainBinding.toolbar.setTitle(R.string.app_name);
        setSupportActionBar(activityMainBinding.toolbar);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        activityMainBinding.content.rvMovies.setLayoutManager(mLayoutManager);
        activityMainBinding.content.rvMovies.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        if (searchManager != null) {
            searchView.setSearchableInfo(
                    searchManager.getSearchableInfo(getComponentName()));
        }

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String searchText) {
                searchMovie(searchText);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do nothing here
                return false;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);
        searchView.setOnCloseListener(() -> {
            activityMainBinding.toolbar.setTitle(R.string.app_name);
            setVisibility(View.GONE, View.VISIBLE, getString(R.string.search_name_of_movie));
            return false;
        });
        searchView.setOnSearchClickListener(v -> activityMainBinding.toolbar.setTitle(""));
        return true;
    }

    private void setVisibility(int recycler, int textView, String txt) {
        activityMainBinding.content.rvMovies.setVisibility(recycler);
        activityMainBinding.content.linSearchTxt.setVisibility(textView);
        activityMainBinding.content.tvMessage.setText(txt);
    }

    /**
     * To call Api to search movie
     *
     * @param searchText text to be searched
     */
    private void searchMovie(String searchText) {
        if (NetworkUtils.isNetworkConnected(getApplicationContext())) {
            if (!searchText.isEmpty()) {
                setVisibility(View.VISIBLE, View.GONE, "");
                NetworkController networkController = new NetworkController();
                networkController.getMovie(this, searchText, Constants.API_KEY);
            } else {
                setVisibility(View.GONE, View.VISIBLE, getString(R.string.search_name_of_movie));
            }
        } else {
            setVisibility(View.GONE, View.VISIBLE, getString(R.string.no_internet_connection));
        }
    }

    @Override
    public void onResponse(MovieResponse movieResponse) {
        if (movieResponse.getResponse().equalsIgnoreCase("True")) {
            setVisibility(View.VISIBLE, View.GONE, "");
            MoviesAdapter mAdapter = new MoviesAdapter(MainActivity.this, movieResponse);
            activityMainBinding.content.rvMovies.setAdapter(mAdapter);
        } else {
            setVisibility(View.GONE, View.VISIBLE, movieResponse.getError());
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        throwable.printStackTrace();
    }
}