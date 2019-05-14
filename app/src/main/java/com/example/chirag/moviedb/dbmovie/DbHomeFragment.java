package com.example.chirag.moviedb.dbmovie;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.chirag.moviedb.R;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.moviedetail.MovieDetailActivity;
import com.example.chirag.moviedb.util.NetworkChangeReceiver;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.chirag.moviedb.data.Constant.CONTENT_MOVIE;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE;
import static com.example.chirag.moviedb.data.Constant.EXTRA_ID;
import static com.example.chirag.moviedb.data.Constant.EXTRA_TITLE;
import static com.example.chirag.moviedb.data.Constant.POSTER_IMAGE_URL;
import static com.example.chirag.moviedb.data.Constant.TYPE_MOBILE;
import static com.example.chirag.moviedb.data.Constant.TYPE_WIFI;

/**
 * MovieDB
 * Created by Chirag on 23/09/18.
 */
public class DbHomeFragment extends Fragment implements DbHomeContract.View, NetworkChangeReceiver.ConnectionListener {

        CardView nowPlayingCardView;
        CardView upcomingCardView;
        CardView popularCardView;
        CardView topRatedCardView;

        RelativeLayout mNoInternet;

        LinearLayout mLinearLayoutMovieHome;
        LinearLayout mLinearLayoutNowPlaying;
        LinearLayout mLinearLayoutTopRated;
        LinearLayout mLinearLayoutUpcoming;

        NetworkChangeReceiver mBroadCastReceiver;
        Context mContext;

        private int mMovieId;
        private boolean isConnected;
        private String mUserEmail;

        public DbHomeFragment () {

        }

        @Override
        public void onCreate (@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

                if (!getArguments().isEmpty()) {
                        mUserEmail = getArguments().getString("EXTRA_EMAIL");
                }
                mBroadCastReceiver = new NetworkChangeReceiver(this);
        }

        @Nullable
        @Override
        public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                View rootView = inflater.inflate(R.layout.movie_home, container, false);

                IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
                mContext.registerReceiver(mBroadCastReceiver, filter);

                mLinearLayoutMovieHome = rootView.findViewById(R.id.movie_popular);
                mLinearLayoutNowPlaying = rootView.findViewById(R.id.movie_now_playing);
                mLinearLayoutTopRated = rootView.findViewById(R.id.movie_top_rated);
                mLinearLayoutUpcoming = rootView.findViewById(R.id.movie_upcoming);

                nowPlayingCardView = rootView.findViewById(R.id.now_playing_card);
                nowPlayingCardView.setVisibility(View.VISIBLE);

                upcomingCardView = rootView.findViewById(R.id.upcoming_card);
                upcomingCardView.setVisibility(View.VISIBLE);

                topRatedCardView = rootView.findViewById(R.id.top_rated_card);
                topRatedCardView.setVisibility(View.VISIBLE);

                popularCardView = rootView.findViewById(R.id.popular_card);
                topRatedCardView.setVisibility(View.VISIBLE);

                mNoInternet = rootView.findViewById(R.id.no_internet);
                mNoInternet.setVisibility(View.GONE);

//                if (isConnected) {
                        popularCardView.setVisibility(View.VISIBLE);
                        upcomingCardView.setVisibility(View.VISIBLE);
                        topRatedCardView.setVisibility(View.VISIBLE);
                        nowPlayingCardView.setVisibility(View.VISIBLE);
//                } else {
//                        popularCardView.setVisibility(View.GONE);
//                        upcomingCardView.setVisibility(View.GONE);
//                        topRatedCardView.setVisibility(View.GONE);
//                        nowPlayingCardView.setVisibility(View.GONE);
//                        mNoInternet.setVisibility(View.VISIBLE);
//                }
                return rootView;
        }


        @Override
        public void onAttach (Context context) {
                super.onAttach(context);
                this.mContext = context;
        }

        @Override
        public void onDestroyView () {
                super.onDestroyView();
                mContext.unregisterReceiver(mBroadCastReceiver);
        }

        @Override
        public void getPopularMovieHome (List<ResultResponse> data) {
                setLayout(data, mLinearLayoutMovieHome);
        }

        @Override
        public void getResultFailure (String errorMessage) {
//                popularCardView.setVisibility(View.GONE);
//                upcomingCardView.setVisibility(View.GONE);
//                topRatedCardView.setVisibility(View.GONE);
//                nowPlayingCardView.setVisibility(View.GONE);
//                mNoInternet.setVisibility(View.VISIBLE);
        }

        @Override
        public void getNowPlayingMovieHome (List<ResultResponse> data) {
                setLayout(data, mLinearLayoutNowPlaying);
        }

        @Override
        public void getTopRatedMovieHome (List<ResultResponse> data) {
                setLayout(data, mLinearLayoutTopRated);
        }

        @Override
        public void getUpcomingMovieHome (List<ResultResponse> data) {
                setLayout(data, mLinearLayoutUpcoming);
        }

        private void startNewActivity (int movieId) {
                Intent intent = new Intent(getContext(), MovieDetailActivity.class);
                intent.putExtra(EXTRA_ID, movieId);
//                intent.putExtra(EXTRA_TITLE, movieName);
                intent.putExtra("EXTRA_EMAIL", mUserEmail);
                intent.putExtra(CONTENT_TYPE, CONTENT_MOVIE);
                startActivity(intent);
        }

        private void setLayout (List<ResultResponse> data, LinearLayout layout) {
                if (data != null && !data.isEmpty()) {

                        layout.removeAllViews();

                        popularCardView.setVisibility(View.VISIBLE);
                        upcomingCardView.setVisibility(View.VISIBLE);
                        topRatedCardView.setVisibility(View.VISIBLE);
                        nowPlayingCardView.setVisibility(View.VISIBLE);
                        mNoInternet.setVisibility(View.GONE);

                        for (final ResultResponse item : data) {
                                View parent = getLayoutInflater().inflate(R.layout.movie_home_poster, layout, false);
                                ImageView poster = parent.findViewById(R.id.movie_home_imageview);
                                String imagePosterString = POSTER_IMAGE_URL + item.getPoster();
                                Picasso.get().load(imagePosterString).into(poster);

                                poster.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick (View view) {
                                                mMovieId = item.getId();
//                                                String movieName = item.getTitle();
                                                startNewActivity(mMovieId);
                                        }
                                });
                                layout.addView(parent);
                        }
                }
        }

        @Override
        public void connectionInfo (Context context, boolean isConnected, int type) {
                this.isConnected = isConnected;
                if (type == TYPE_WIFI) {
                        Toast.makeText(context, "Connected to Wifi", Toast.LENGTH_SHORT).show();
                } else if (type == TYPE_MOBILE) {
                        Toast.makeText(context, "Connected to Mobile data", Toast.LENGTH_SHORT).show();
                } else {
                        Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
                DbHomePresenter presenter = new DbHomePresenter(context, isConnected);
                presenter.attachView(this);
        }
}
