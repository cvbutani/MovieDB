package com.example.chirag.moviedb.dbtv;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chirag.moviedb.R;
import com.example.chirag.moviedb.model.HeaderItem;
import com.example.chirag.moviedb.model.ResultHeaderItem;
import com.squareup.picasso.Picasso;

/**
 * MovieDB
 * Created by Chirag on 23/09/18.
 */
public class DBTvActivity extends AppCompatActivity implements DBTvContract.View {

    private LinearLayout mLinearLayoutMovieHome;

    private TextView mTextViewPopular;

    private static final String POSTER_IMAGE_URL = "http://image.tmdb.org/t/p/w185/";

    int mMovieId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbhome);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DBTvPresenter presenter = new DBTvPresenter();
        presenter.attachView(this);

        mLinearLayoutMovieHome = findViewById(R.id.movie_popular);
        mTextViewPopular = findViewById(R.id.popular_label);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPopularTvSuccess(HeaderItem data) {
        for (final ResultHeaderItem item : data.getResults()) {
            View parent = getLayoutInflater().inflate(R.layout.movie_home_poster, mLinearLayoutMovieHome, false);
            ImageView poster = parent.findViewById(R.id.movie_home_imageview);
            mTextViewPopular.setText("Popular TV Shows");
            StringBuilder builder = new StringBuilder();
            String imagePosterString = builder.append(POSTER_IMAGE_URL).append(item.getPoster()).toString();
            Picasso.get().load(imagePosterString).into(poster);

//            poster.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mMovieId = item.getId();
//                    String movieName = item.getTitle();
//                    startNewActivity(mMovieId, movieName);
//
//                }
//            });
            mLinearLayoutMovieHome.addView(parent);
        }
    }

    @Override
    public void onPopularTvFailure(String errorMessage) {

    }
}
