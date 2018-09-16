package com.example.chirag.moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chirag.moviedb.model.GenreItem;
import com.example.chirag.moviedb.model.HeaderItem;
import com.example.chirag.moviedb.model.ResultHeaderItem;
import com.example.chirag.moviedb.model.TrailerItem;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailContract.View {

    ImageView mImageViewAppBar;

    ImageView mImageViewPoster;

    TextView mTextViewReleaseDate;

    TextView mTextViewLanguage;

    TextView mTextViewGenre;

    TextView mTextViewRating;

    TextView mTextViewOverview;

    ResultHeaderItem mHeaderItem;

//    ResultHeaderItem mResultHeaderItem;

    GenreItem mGenreItem;

    int mMovieId;

    MovieDetailPresenter mPresenter;

    public static final String LOG_TAG = "MOVIE DETAIL ACTIVITY ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if (getIntent() != null) {
            Intent i = getIntent();
            mMovieId = getIntent().getExtras().getInt("EXTRA");
            mGenreItem = (GenreItem) i.getSerializableExtra("EXTRA_GENRE");
        }
        Log.i(LOG_TAG, mMovieId + "  --  ID");
        mPresenter = new MovieDetailPresenter();
        mPresenter.attachView(this, mMovieId);

        viewHolder();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mPresenter.getGenreItem(mHeaderItem);
    }

    private void viewHolder() {
        mImageViewAppBar = findViewById(R.id.imageViewCollapsing);
        mTextViewReleaseDate = findViewById(R.id.listview_item_release_date);
        mTextViewLanguage = findViewById(R.id.listview_item_language);
        mTextViewGenre = findViewById(R.id.listview_item_genre);
        mTextViewRating = findViewById(R.id.listview_item_rating);
        mImageViewPoster = findViewById(R.id.listview_item_image);
        mTextViewOverview = findViewById(R.id.movie_detail_overview);
    }

    private String genreId() {
        StringBuilder genre = new StringBuilder();
        int size = mGenreItem.getResultGenreItems().size();

        for (int j = 0; j < size; j++) {
            if (mGenreItem.getResultGenreItems().get(j).getId().equals(mHeaderItem.getGenreId().get(0))) {
                genre.append(mGenreItem.getResultGenreItems().get(j).getName());
            }
        }
        for (int j1 = 0; j1 < size; j1++) {
            for (int k = 1; k < mHeaderItem.getGenreId().size(); k++) {
                if (mHeaderItem.getGenreId().get(k).equals(mGenreItem.getResultGenreItems().get(j1).getId())) {
                    genre.append(", ");
                    genre.append(mGenreItem.getResultGenreItems().get(j1).getName());
                }
            }
        }

        return genre.toString();
    }

    @Override
    public void onTrailerListSuccess(TrailerItem data) {
        if (!data.getResults().isEmpty()) {
            Log.i("RESULT ", data.getResults().get(0).getKey());


        } else {
            Log.i("TRAILER ERROR ", "SOMETHING WENT WRONG");
        }
    }

    @Override
    public void onTrailerListFailure(String errorMessage) {

    }

    @Override
    public void onMovieDetail(HeaderItem data, int movieId) {
        for (int i = 0; i < data.getResults().size(); i++) {
            if (movieId == data.getResults().get(i).getId()) {
                mHeaderItem = data.getResults().get(i);

                String movieReleaseDate = mHeaderItem.getReleaseDate();

                Log.i(LOG_TAG, movieReleaseDate);
                String movieLanguage = mHeaderItem.getOriginalLanguage();
                Log.i(LOG_TAG, movieLanguage);
                double movieRating = mHeaderItem.getVoteAverage();
                Log.i(LOG_TAG, movieReleaseDate);
                String movieOverview = mHeaderItem.getDescription();
                Log.i(LOG_TAG, movieOverview);
                StringBuilder builder = new StringBuilder();
                String imageBackDropString = builder.append("http://image.tmdb.org/t/p/w780/").append(mHeaderItem.getBackdropPath()).toString();

                builder = new StringBuilder();
                String imagePosterString = builder.append("http://image.tmdb.org/t/p/w185/").append(mHeaderItem.getPoster()).toString();

                Log.i(LOG_TAG, imageBackDropString);
                Log.i(LOG_TAG, imagePosterString);

                Picasso.get().load(imageBackDropString).into(mImageViewAppBar);
                Picasso.get().load(imagePosterString).into(mImageViewPoster);
                mTextViewReleaseDate.setText(movieReleaseDate);
                mTextViewLanguage.setText(movieLanguage);
                mTextViewGenre.setText(genreId());
                mTextViewRating.setText(String.valueOf(movieRating));
                mTextViewOverview.setText(movieOverview);
            }
        }
    }

    @Override
    public void onGenreDetail(GenreItem data, ResultHeaderItem item) {
//        StringBuilder genre = new StringBuilder();
//        int size = data.getResultGenreItems().size();
//
//        for (int j = 0; j < size; j++) {
//            if (data.getResultGenreItems().get(j).getId().equals(item.getGenreId().get(0))) {
//                genre.append(data.getResultGenreItems().get(j).getName());
//            }
//        }
//        for (int j1 = 0; j1 < size; j1++) {
//            for (int k = 1; k < item.getGenreId().size(); k++) {
//                if (item.getGenreId().get(k).equals(data.getResultGenreItems().get(j1).getId())) {
//                    genre.append(", ");
//                    genre.append(data.getResultGenreItems().get(j1).getName());
//                }
//            }
//        }
//        Log.i("GENRE ITEM ", genre.toString());
    }
}
