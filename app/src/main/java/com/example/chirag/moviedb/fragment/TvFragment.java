package com.example.chirag.moviedb.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chirag.moviedb.R;
import com.example.chirag.moviedb.dbtv.DBTvContract;
import com.example.chirag.moviedb.dbtv.DBTvPresenter;
import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.data.model.Movies;
import com.example.chirag.moviedb.data.model.MovieResponse;
import com.example.chirag.moviedb.moviedetail.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import static com.example.chirag.moviedb.data.Constant.CONTENT_TV;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE;
import static com.example.chirag.moviedb.data.Constant.EXTRA_GENRE;
import static com.example.chirag.moviedb.data.Constant.EXTRA_ID;
import static com.example.chirag.moviedb.data.Constant.EXTRA_TITLE;

/**
 * MovieDB
 * Created by Chirag on 23/09/18.
 */
public class TvFragment extends Fragment implements DBTvContract.View {

    private Genre mGenreList;

    private LinearLayout mLinearLayoutMovieHome;
    private LinearLayout mLinearLayoutNowPlaying;
    private LinearLayout mLinearLayoutTopRated;
    private LinearLayout mLinearLayoutUpcoming;

    private TextView mTextViewTopRated;
    TextView mTextViewPopular;

    private ConnectivityManager mConnectivityManager;
    private NetworkInfo mActiveNetwork;

    private static final String POSTER_IMAGE_URL = "http://image.tmdb.org/t/p/w185/";

    int mMovieId;

    public TvFragment() {
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
        mLinearLayoutTopRated = rootView.findViewById(R.id.movie_top_rated);
        mLinearLayoutUpcoming = rootView.findViewById(R.id.movie_upcoming);
        mTextViewTopRated = rootView.findViewById(R.id.movie_top_rated_label);
        mTextViewPopular = rootView.findViewById(R.id.popular_label);

        return rootView;
    }

    @Override
    public void onPopularTvSuccess(Movies data) {
        setLayout(data, mLinearLayoutMovieHome, mTextViewPopular, "Popular TV Shows");
    }

    @Override
    public void onPopularTvFailure(String errorMessage) {

    }

    @Override
    public void getTVTopRatedContentSuccess(Movies data) {
        setLayout(data,mLinearLayoutTopRated, mTextViewTopRated, "Top Rated TV Shows");
    }

    @Override
    public void getTVTopRatedContentFailure(String errorMessage) {

    }

    private void startNewActivity(int movieId, String name) {
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra(EXTRA_ID, movieId);
        intent.putExtra(EXTRA_TITLE, name);
        intent.putExtra(EXTRA_GENRE, mGenreList);
        intent.putExtra(CONTENT_TYPE, CONTENT_TV);
        startActivity(intent);
    }

    private void setLayout(Movies data, LinearLayout layout, TextView textView, String title) {
        layout.removeAllViews();
        textView.setText(title);
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
                    String movieName = item.getOriginalName();
                    startNewActivity(mMovieId, movieName);
                }
            });
            layout.addView(parent);
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
