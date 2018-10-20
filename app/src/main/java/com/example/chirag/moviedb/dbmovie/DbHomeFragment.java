package com.example.chirag.moviedb.dbmovie;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.chirag.moviedb.R;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.dbmovie.DbHomeContract;
import com.example.chirag.moviedb.dbmovie.DbHomePresenter;
import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.moviedetail.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import static com.example.chirag.moviedb.data.Constant.CONTENT_MOVIE;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE;
import static com.example.chirag.moviedb.data.Constant.EXTRA_ID;
import static com.example.chirag.moviedb.data.Constant.EXTRA_TITLE;

/**
 * MovieDB
 * Created by Chirag on 23/09/18.
 */
public class DbHomeFragment extends Fragment implements DbHomeContract.View {

    private LinearLayout mLinearLayoutMovieHome;
    private LinearLayout mLinearLayoutNowPlaying;
    private LinearLayout mLinearLayoutTopRated;
    private LinearLayout mLinearLayoutUpcoming;

    private ConnectivityManager mConnectivityManager;
    private NetworkInfo mActiveNetwork;

    private static final String POSTER_IMAGE_URL = "http://image.tmdb.org/t/p/w185/";

    int mMovieId;

    public DbHomeFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isConnected = checkInternetConnection();

        DbHomePresenter presenter = new DbHomePresenter(getContext(), isConnected);
        presenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_home, container, false);
        mLinearLayoutMovieHome = rootView.findViewById(R.id.movie_popular);
        mLinearLayoutNowPlaying = rootView.findViewById(R.id.movie_now_playing);
        mLinearLayoutTopRated = rootView.findViewById(R.id.movie_top_rated);
        mLinearLayoutUpcoming = rootView.findViewById(R.id.movie_upcoming);
        CardView cardView = rootView.findViewById(R.id.now_playing_card);
        cardView.setVisibility(View.VISIBLE);
        CardView upcomingCardView = rootView.findViewById(R.id.upcoming_card);
        upcomingCardView.setVisibility(View.VISIBLE);
        return rootView;
    }

    @Override
    public void getPopularMovieHome(Result data) {
        setLayout(data, mLinearLayoutMovieHome);
    }

    @Override
    public void getResultFailure(String errorMessage) {

    }

    @Override
    public void getNowPlayingMovieHome(Result data) {
        setLayout(data, mLinearLayoutNowPlaying);
    }

    @Override
    public void getTopRatedMovieHome(Result data) {
        setLayout(data, mLinearLayoutTopRated);
    }

    @Override
    public void getUpcomingMovieHome(Result data) {
        setLayout(data, mLinearLayoutUpcoming);
    }

    private void startNewActivity(int movieId, String movieName) {
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra(EXTRA_ID, movieId);
        intent.putExtra(EXTRA_TITLE, movieName);
        intent.putExtra(CONTENT_TYPE, CONTENT_MOVIE);
        startActivity(intent);
    }

    private void setLayout(Result data, LinearLayout layout) {
        layout.removeAllViews();
        for (final ResultResponse item : data.getResults()) {
            View parent = getLayoutInflater().inflate(R.layout.movie_home_poster, layout, false);
            ImageView poster = parent.findViewById(R.id.movie_home_imageview);
            StringBuilder builder = new StringBuilder();
            String imagePosterString = builder.append(POSTER_IMAGE_URL).append(item.getPoster()).toString();
            Picasso.get().load(imagePosterString).into(poster);

            poster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mMovieId = item.getId();
                    String movieName = item.getTitle();
                    startNewActivity(mMovieId, movieName);

                }
            });
            layout.addView(parent);
        }
    }

    public boolean checkInternetConnection() {

        if (getActivity() != null) {
            mConnectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        if (mConnectivityManager != null) {
            mActiveNetwork = mConnectivityManager.getActiveNetworkInfo();
        }

        return (mActiveNetwork != null) && (mActiveNetwork.isConnectedOrConnecting());
    }
}
