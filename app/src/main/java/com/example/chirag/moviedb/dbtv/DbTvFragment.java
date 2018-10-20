package com.example.chirag.moviedb.dbtv;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chirag.moviedb.R;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.dbtv.DBTvContract;
import com.example.chirag.moviedb.dbtv.DBTvPresenter;
import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.moviedetail.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import static com.example.chirag.moviedb.data.Constant.CONTENT_TV;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE;
import static com.example.chirag.moviedb.data.Constant.EXTRA_ID;
import static com.example.chirag.moviedb.data.Constant.EXTRA_TITLE;

/**
 * MovieDB
 * Created by Chirag on 23/09/18.
 */
public class DbTvFragment extends Fragment implements DBTvContract.View {

    CardView nowPlayingCardView;
    CardView upcomingCardView;
    CardView popularCardView;
    CardView topRatedCardView;

    RelativeLayout mNoInternet;

    private LinearLayout mLinearLayoutMovieHome;
    private LinearLayout mLinearLayoutNowPlaying;
    private LinearLayout mLinearLayoutTopRated;
    private LinearLayout mLinearLayoutUpcoming;

    private TextView mTextViewTopRated;
    TextView mTextViewPopular;
    TextView mTextViewLatest;

    private ConnectivityManager mConnectivityManager;
    private NetworkInfo mActiveNetwork;

    private static final String POSTER_IMAGE_URL = "http://image.tmdb.org/t/p/w185/";

    int mMovieId;

    public DbTvFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DBTvPresenter tvPresenter = new DBTvPresenter(getContext(), checkInternetConnection());
        tvPresenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_home, container, false);
        mLinearLayoutMovieHome = rootView.findViewById(R.id.movie_popular);
        mLinearLayoutNowPlaying = rootView.findViewById(R.id.movie_now_playing);
        nowPlayingCardView = rootView.findViewById(R.id.now_playing_card);
        nowPlayingCardView.setVisibility(View.GONE);
        upcomingCardView = rootView.findViewById(R.id.upcoming_card);
        upcomingCardView.setVisibility(View.GONE);
        topRatedCardView = rootView.findViewById(R.id.top_rated_card);
        popularCardView = rootView.findViewById(R.id.popular_card);
        mLinearLayoutTopRated = rootView.findViewById(R.id.movie_top_rated);
        mLinearLayoutUpcoming = rootView.findViewById(R.id.movie_upcoming);
        mTextViewTopRated = rootView.findViewById(R.id.movie_top_rated_label);
        mTextViewPopular = rootView.findViewById(R.id.popular_label);
        mTextViewLatest = rootView.findViewById(R.id.now_playing_label);
        mNoInternet = rootView.findViewById(R.id.no_internet);
        if (checkInternetConnection()) {
            popularCardView.setVisibility(View.VISIBLE);
            topRatedCardView.setVisibility(View.VISIBLE);
            mNoInternet.setVisibility(View.GONE);
        } else {
            popularCardView.setVisibility(View.GONE);
            upcomingCardView.setVisibility(View.GONE);
            topRatedCardView.setVisibility(View.GONE);
            nowPlayingCardView.setVisibility(View.GONE);
            mNoInternet.setVisibility(View.VISIBLE);
        }
        return rootView;
    }

    @Override
    public void getPopularTvHome(Result data) {
        setLayout(data, mLinearLayoutMovieHome, mTextViewPopular, "Popular TV Shows");
    }

    @Override
    public void getResultFailure(String errorMessage) {

    }

    @Override
    public void getTopRatedTvHome(Result data) {
        setLayout(data, mLinearLayoutTopRated, mTextViewTopRated, "Top Rated TV Shows");
    }

    private void startNewActivity(int movieId, String name) {
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra(EXTRA_ID, movieId);
        intent.putExtra(EXTRA_TITLE, name);
        intent.putExtra(CONTENT_TYPE, CONTENT_TV);
        startActivity(intent);
    }

    private void setLayout(Result data, LinearLayout layout, TextView textView, String title) {
        if (checkInternetConnection()) {
            if (data != null && !data.getResults().isEmpty()) {
                popularCardView.setVisibility(View.VISIBLE);
                topRatedCardView.setVisibility(View.VISIBLE);
                layout.removeAllViews();
                textView.setText(title);
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
                            String movieName = item.getName();
                            startNewActivity(mMovieId, movieName);
                        }
                    });
                    layout.addView(parent);
                }
            }
        } else {
            popularCardView.setVisibility(View.GONE);
            upcomingCardView.setVisibility(View.GONE);
            topRatedCardView.setVisibility(View.GONE);
            nowPlayingCardView.setVisibility(View.GONE);
        }
    }

    private boolean checkInternetConnection() {

        if (getActivity() != null) {
            mConnectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        if (mConnectivityManager != null) {
            mActiveNetwork = mConnectivityManager.getActiveNetworkInfo();
        }

        return (mActiveNetwork != null) && (mActiveNetwork.isConnectedOrConnecting());
    }
}
