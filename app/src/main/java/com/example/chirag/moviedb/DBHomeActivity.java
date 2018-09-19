package com.example.chirag.moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.chirag.moviedb.model.GenreItem;
import com.example.chirag.moviedb.model.HeaderItem;
import com.example.chirag.moviedb.model.ResultHeaderItem;
import com.example.chirag.moviedb.model.TrailerItem;
import com.squareup.picasso.Picasso;

//import com.example.chirag.moviedb.model.ResultTrailerItem;

public class DBHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DbHomeContract.View {

    //    private ExpandableListView mExpandableListView;
//    private ExpandableListViewAdapter mExpandableListViewAdapter;
    private HeaderItem mViewheader;
    private GenreItem mGenreList;
    private TrailerItem mTrailerItem;
    private LinearLayout mLinearLayoutMovieHome;
    private LinearLayout mLinearLayoutNowPlaying;
    private static final String POSTER_IMAGE_URL = "http://image.tmdb.org/t/p/w185/";
    private int lastExpandedPosition = -1;
    DbHomePresenter mPresenter;
    MovieDetailContract mMovieDetailCallback;
    int mMovieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbhome);

        mPresenter = new DbHomePresenter();
        mPresenter.attachView(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        mExpandableListView = findViewById(R.id.expandable_listview);
//
//        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
//                mMovieId = i;
//                startNewActivity(i);
//                return true;
//            }
//        });
        mLinearLayoutMovieHome = findViewById(R.id.movie_popular);
        mLinearLayoutNowPlaying = findViewById(R.id.movie_now_playing);

    }

    private void startNewActivity(int movieId) {
        Intent intent = new Intent(DBHomeActivity.this, MovieDetailActivity.class);
        intent.putExtra("EXTRA", movieId);
        intent.putExtra("EXTRA_GENRE", mGenreList);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dbhome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onHeaderResultSuccess(HeaderItem data) {
        mViewheader = data;
//        mExpandableListViewAdapter = new ExpandableListViewAdapter(this, mViewheader, mGenreList);
//        mExpandableListView.setAdapter(mExpandableListViewAdapter);
//        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//
//            @Override
//            public void onGroupExpand(int i) {
//                if (lastExpandedPosition != -1 && i != lastExpandedPosition) {
//                    mExpandableListView.collapseGroup(lastExpandedPosition);
//                }
//                lastExpandedPosition = i;
//            }
//        });

        for (final ResultHeaderItem item : mViewheader.getResults()) {
            View parent = getLayoutInflater().inflate(R.layout.movie_home_poster, mLinearLayoutMovieHome, false);
            ImageView poster = parent.findViewById(R.id.movie_home_imageview);
            StringBuilder builder = new StringBuilder();
            String imagePosterString = builder.append(POSTER_IMAGE_URL).append(item.getPoster()).toString();
            Picasso.get().load(imagePosterString).into(poster);

            poster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mMovieId = item.getId();
                    startNewActivity(mMovieId);
                }
            });
            mLinearLayoutMovieHome.addView(parent);
        }

    }

    @Override
    public void onHeaderResultFailure(String errorMessage) {
    }

    @Override
    public void onGenreListSuccess(GenreItem data) {
        mGenreList = data;
    }

    @Override
    public void onGenreListFailure(String errorMessage) {

    }

    @Override
    public void onNowPlayingMovieSuccess(HeaderItem data) {

        Log.i("NOW PLAYING: ", data.getResults().get(0).getTitle());
        for (final ResultHeaderItem item : data.getResults()) {
            View parent = getLayoutInflater().inflate(R.layout.movie_home_poster, mLinearLayoutNowPlaying, false);
            ImageView poster = parent.findViewById(R.id.movie_home_imageview);
            StringBuilder builder = new StringBuilder();
            String imagePosterString = builder.append(POSTER_IMAGE_URL).append(item.getPoster()).toString();
            Picasso.get().load(imagePosterString).into(poster);

            poster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mMovieId = item.getId();
                    startNewActivity(mMovieId);
                }
            });
            mLinearLayoutNowPlaying.addView(parent);
        }
    }

    @Override
    public void onNowPlayingMovieFailure(String errorMessage) {

    }

}

