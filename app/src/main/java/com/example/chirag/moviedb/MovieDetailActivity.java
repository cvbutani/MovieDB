package com.example.chirag.moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chirag.moviedb.model.GenreItem;
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

    GenreItem mGenreItem;

    MovieDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if (getIntent() != null) {
            Intent i = getIntent();
            mHeaderItem = (ResultHeaderItem) i.getSerializableExtra("EXTRA");
            mGenreItem = (GenreItem) i.getSerializableExtra("EXTRA_GENRE");
        }

        mPresenter = new MovieDetailPresenter();
        mPresenter.attachView(this, mHeaderItem.getId());

        String movieReleaseDate = mHeaderItem.getReleaseDate();
        String movieLanguage = mHeaderItem.getOriginalLanguage();
        double movieRating = mHeaderItem.getVoteAverage();
        String movieOverview = mHeaderItem.getDescription();

        Log.i("OVERVIEW ", movieOverview);
        viewHolder();

        StringBuilder builder = new StringBuilder();
        String imageBackDropString = builder.append("http://image.tmdb.org/t/p/w780/").append(mHeaderItem.getBackdropPath()).toString();

        builder = new StringBuilder();
        String imagePosterString = builder.append("http://image.tmdb.org/t/p/w185/").append(mHeaderItem.getPoster()).toString();

        Picasso.get().load(imageBackDropString).into(mImageViewAppBar);
        Picasso.get().load(imagePosterString).into(mImageViewPoster);
        mTextViewReleaseDate.setText(movieReleaseDate);
        mTextViewLanguage.setText(movieLanguage);
        mTextViewGenre.setText(genreId());
        mTextViewRating.setText(String.valueOf(movieRating));
        mTextViewOverview.setText(movieOverview);

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
        Log.i("RESULT ", data.getResults().get(0).getKey());
    }

    @Override
    public void onTrailerListFailure(String errorMessage) {

    }
}
