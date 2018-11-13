package com.example.chirag.moviedb.dbtv;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.widget.Toast;

import com.example.chirag.moviedb.R;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.dbtv.DBTvContract;
import com.example.chirag.moviedb.dbtv.DBTvPresenter;
import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.moviedetail.MovieDetailActivity;
import com.example.chirag.moviedb.util.NetworkChangeReceiver;
import com.squareup.picasso.Picasso;

import static com.example.chirag.moviedb.data.Constant.CONTENT_TV;
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
public class DbTvFragment extends Fragment implements DBTvContract.View, NetworkChangeReceiver.ConnectionListener {

        CardView nowPlayingCardView;
        CardView upcomingCardView;
        CardView popularCardView;
        CardView topRatedCardView;

        RelativeLayout mNoInternet;

        LinearLayout mLinearLayoutMovieHome;
        LinearLayout mLinearLayoutNowPlaying;
        LinearLayout mLinearLayoutTopRated;
        LinearLayout mLinearLayoutUpcoming;

        TextView mTextViewTopRated;
        TextView mTextViewPopular;
        TextView mTextViewLatest;

        NetworkChangeReceiver mBroadCastReceiver;
        Context mContext;

        private int mMovieId;
        private boolean isConnected;
        private String mUserEmail;

        public DbTvFragment () {
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
                nowPlayingCardView.setVisibility(View.GONE);

                upcomingCardView = rootView.findViewById(R.id.upcoming_card);
                upcomingCardView.setVisibility(View.GONE);

                topRatedCardView = rootView.findViewById(R.id.top_rated_card);

                popularCardView = rootView.findViewById(R.id.popular_card);

                mTextViewTopRated = rootView.findViewById(R.id.movie_top_rated_label);
                mTextViewPopular = rootView.findViewById(R.id.popular_label);
                mTextViewLatest = rootView.findViewById(R.id.now_playing_label);

                mNoInternet = rootView.findViewById(R.id.no_internet);
                if (isConnected) {
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
        public void getPopularTvHome (Result data) {
                setLayout(data, mLinearLayoutMovieHome, mTextViewPopular, "Popular TV Shows");
        }

        @Override
        public void getResultFailure (String errorMessage) {

        }

        @Override
        public void getTopRatedTvHome (Result data) {
                setLayout(data, mLinearLayoutTopRated, mTextViewTopRated, "Top Rated TV Shows");
        }

        private void startNewActivity (int movieId, String name) {
                Intent intent = new Intent(getContext(), MovieDetailActivity.class);
                intent.putExtra(EXTRA_ID, movieId);
                intent.putExtra(EXTRA_TITLE, name);
                intent.putExtra("EXTRA_EMAIL", mUserEmail);
                intent.putExtra(CONTENT_TYPE, CONTENT_TV);
                startActivity(intent);
        }

        private void setLayout (Result data, LinearLayout layout, TextView textView, String title) {
                if (isConnected) {
                        if (data != null && !data.getResults().isEmpty()) {
                                popularCardView.setVisibility(View.VISIBLE);
                                topRatedCardView.setVisibility(View.VISIBLE);
                                layout.removeAllViews();
                                textView.setText(title);
                                for (final ResultResponse item : data.getResults()) {
                                        View parent = getLayoutInflater().inflate(R.layout.movie_home_poster, layout, false);
                                        ImageView poster = parent.findViewById(R.id.movie_home_imageview);
                                        String imagePosterString = POSTER_IMAGE_URL + item.getPoster();
                                        Picasso.get().load(imagePosterString).into(poster);

                                        poster.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick (View view) {
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
                DBTvPresenter tvPresenter = new DBTvPresenter(context, isConnected);
                tvPresenter.attachView(this);
        }
}
