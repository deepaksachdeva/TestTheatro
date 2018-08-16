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

/**
 * MainActivity to search movie from search option
 * Created by deepak sachdeva on 14/08/17.
 *
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
        return true;
    }

    /**
     * To call Api to search movie
     * @param searchText text to be searched
     */
    private void searchMovie(String searchText) {
        if (!searchText.equals("")) {
            activityMainBinding.content.linSearchTxt.setVisibility(View.GONE);
            activityMainBinding.content.rvMovies.setVisibility(View.VISIBLE);
            NetworkController networkController = new NetworkController();
            networkController.getMovie(this, searchText, Constants.API_KEY);
        } else {
            activityMainBinding.content.rvMovies.setVisibility(View.GONE);
            activityMainBinding.content.linSearchTxt.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResponse(MovieResponse movieResponse) {
        MoviesAdapter mAdapter = new MoviesAdapter(MainActivity.this, movieResponse);
        activityMainBinding.content.rvMovies.setAdapter(mAdapter);
    }

    @Override
    public void onFailure(Throwable throwable) {
        throwable.printStackTrace();
    }
}