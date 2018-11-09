package com.example.chirag.moviedb.favourite;

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
import com.example.chirag.moviedb.data.model.Favourite;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.data.model.TMDB;
import com.example.chirag.moviedb.moviedetail.MovieDetailActivity;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.chirag.moviedb.data.Constant.CONTENT_MOVIE;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TV;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE;
import static com.example.chirag.moviedb.data.Constant.EXTRA_ID;
import static com.example.chirag.moviedb.data.Constant.EXTRA_TITLE;
import static com.example.chirag.moviedb.data.Constant.POSTER_IMAGE_URL;

/**
 * MovieDB
 * Created by Chirag on 22/10/18.
 */
public class FavouriteFragment extends Fragment implements FavouriteContract.View {

        CardView nowPlayingCardView;
        CardView upcomingCardView;
        CardView popularCardView;
        CardView topRatedCardView;

        LinearLayout mLinearLayoutMovieHome;
        LinearLayout mLinearLayoutTopRated;

        TextView mTextViewTopRated;
        TextView mTextViewPopular;

        RelativeLayout mNoInternet;

        ConnectivityManager mConnectivityManager;
        NetworkInfo mActiveNetwork;

        String mUserEmail;git
        String mContentType;

        FavouritePresenter mPresenter;

        public FavouriteFragment() {
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                Logger.addLogAdapter(new AndroidLogAdapter());
                if (!getArguments().isEmpty()) {
                        if (getArguments().containsKey("EXTRA_EMAIL")) {
                                mUserEmail = getArguments().getString("EXTRA_EMAIL");
                        }
                }
                boolean isConnected = checkInternetConnection();

                mPresenter = new FavouritePresenter(getContext(), isConnected);
                mPresenter.attachView(this, mUserEmail);
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                View rootView = inflater.inflate(R.layout.movie_home, container, false);

                mLinearLayoutMovieHome = rootView.findViewById(R.id.movie_popular);
                mLinearLayoutTopRated = rootView.findViewById(R.id.movie_top_rated);

                nowPlayingCardView = rootView.findViewById(R.id.now_playing_card);
                nowPlayingCardView.setVisibility(View.GONE);

                upcomingCardView = rootView.findViewById(R.id.upcoming_card);
                upcomingCardView.setVisibility(View.GONE);

                topRatedCardView = rootView.findViewById(R.id.top_rated_card);

                popularCardView = rootView.findViewById(R.id.popular_card);

                mNoInternet = rootView.findViewById(R.id.no_internet);
                mNoInternet.setVisibility(View.GONE);

                mTextViewTopRated = rootView.findViewById(R.id.movie_top_rated_label);
                mTextViewPopular = rootView.findViewById(R.id.popular_label);

                if (checkInternetConnection()) {
                        popularCardView.setVisibility(View.VISIBLE);
                        topRatedCardView.setVisibility(View.VISIBLE);
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
        public void getFavouriteTMDBInfo(final List<Favourite> data) {
                if (data != null) {
                        mLinearLayoutMovieHome.removeAllViews();
                        mLinearLayoutTopRated.removeAllViews();
                        popularCardView.setVisibility(View.VISIBLE);
                        topRatedCardView.setVisibility(View.VISIBLE);
                        mNoInternet.setVisibility(View.GONE);
                        for (final Favourite info : data) {
                                if (info.getContent_type().equals(CONTENT_MOVIE)) {
                                        setLayout(info, mLinearLayoutMovieHome, mTextViewPopular, "Favourite Movies");
                                } else if (info.getContent_type().equals(CONTENT_TV)) {
                                        setLayout(info, mLinearLayoutTopRated, mTextViewTopRated,"Favourite Tv Shows");
                                }
                        }
                } else {
                        popularCardView.setVisibility(View.GONE);
                        topRatedCardView.setVisibility(View.GONE);
                }
        }

        @Override
        public void getFavouriteTMDBFailure(String errorMessage) {

        }

        private void startNewActivity(int movieId, String movieName) {
                Intent intent = new Intent(getContext(), MovieDetailActivity.class);
                intent.putExtra(EXTRA_ID, movieId);
                intent.putExtra(EXTRA_TITLE, movieName);
                intent.putExtra(CONTENT_TYPE, mContentType);
                startActivity(intent);
        }

        private void setLayout(Favourite info, LinearLayout layout, TextView textView, String title) {
                textView.setText(title);
                View parent = getLayoutInflater().inflate(R.layout.movie_home_poster, layout, false);
                ImageView poster = parent.findViewById(R.id.movie_home_imageview);
                String imagePosterString = POSTER_IMAGE_URL + info.getPoster();
                Picasso.get().load(imagePosterString).into(poster);
                mContentType = info.getContent_type();
                poster.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                int movieId = info.getId();
                                String movieName = info.getTitle();
                                startNewActivity(movieId, movieName);
                        }
                });
                layout.addView(parent);
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
