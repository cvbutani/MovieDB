package com.example.chirag.moviedb.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.chirag.moviedb.R;
import com.example.chirag.moviedb.data.model.MovieResponse;
import com.example.chirag.moviedb.dbmovie.DbHomeContract;
import com.example.chirag.moviedb.dbmovie.DbHomePresenter;
import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.data.model.Movies;
import com.example.chirag.moviedb.moviedetail.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import static com.example.chirag.moviedb.data.Constant.CONTENT_MOVIE;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE;
import static com.example.chirag.moviedb.data.Constant.EXTRA_GENRE;
import static com.example.chirag.moviedb.data.Constant.EXTRA_ID;
import static com.example.chirag.moviedb.data.Constant.EXTRA_TITLE;

/**
 * MovieDB
 * Created by Chirag on 23/09/18.
 */
public class MovieFragment extends Fragment implements DbHomeContract.View {

    private Genre mGenreList;

    private LinearLayout mLinearLayoutMovieHome;
    private LinearLayout mLinearLayoutNowPlaying;
    private LinearLayout mLinearLayoutTopRated;
    private LinearLayout mLinearLayoutUpcoming;

    private ConnectivityManager mConnectivityManager;
    private NetworkInfo mActiveNetwork;

    private static final String POSTER_IMAGE_URL = "http://image.tmdb.org/t/p/w185/";

    int mMovieId;

    public MovieFragment() {

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

        return rootView;

    }

    @Override
    public void onHeaderResultSuccess(Movies data) {
        setLayout(data, mLinearLayoutMovieHome);
    }

    @Override
    public void onHeaderResultFailure(String errorMessage) {

    }

    @Override
    public void onGenreListSuccess(Genre data) {
        mGenreList = data;
    }

    @Override
    public void onGenreListFailure(String errorMessage) {

    }

    @Override
    public void onNowPlayingMovieSuccess(Movies data) {
        setLayout(data, mLinearLayoutNowPlaying);
    }

    @Override
    public void onNowPlayingMovieFailure(String errorMessage) {

    }

    @Override
    public void onTopRatedMovieSuccess(Movies data) {
        setLayout(data, mLinearLayoutTopRated);
    }

    @Override
    public void onTopRatedMovieFailure(String errorMessage) {

    }

    @Override
    public void onUpcomingMovieSuccess(Movies data) {
        setLayout(data, mLinearLayoutUpcoming);
    }

    @Override
    public void onUpcomingMovieFailure(String errorMessage) {

    }

    private void startNewActivity(int movieId, String name) {
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra(EXTRA_ID, movieId);
        intent.putExtra(EXTRA_TITLE, name);
        intent.putExtra(EXTRA_GENRE, mGenreList);
        intent.putExtra(CONTENT_TYPE, CONTENT_MOVIE);
        startActivity(intent);
    }

    private void setLayout(Movies data, LinearLayout layout) {
        layout.removeAllViews();
        for (final MovieResponse item : data.getResults()) {
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
