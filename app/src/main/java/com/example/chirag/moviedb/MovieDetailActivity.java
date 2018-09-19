package com.example.chirag.moviedb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.CardView;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chirag.moviedb.model.GenreItem;
import com.example.chirag.moviedb.model.HeaderItem;
import com.example.chirag.moviedb.model.ResultHeaderItem;
import com.example.chirag.moviedb.model.ResultTrailerItem;
import com.example.chirag.moviedb.model.ReviewResponse;
import com.example.chirag.moviedb.model.Reviews;
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
    TextView mTextViewTrailer;

    LinearLayout mLinearLayoutTrailer;
    LinearLayout mLinearLayoutReview;

    ResultHeaderItem mHeaderItem;
    GenreItem mGenreItem;
    MovieDetailPresenter mPresenter;


    CardView mTrailerCardView;
    CardView mReviewCardView;

    private int mMovieId;
    private String mMovieName;

    private boolean isContentClicked = false;

    private static final String YOUTUBE_URL = "http://www.youtube.com/watch?v=%s";
    private static final String YOUTUBE_THUMBNAIL_URL = "http://img.youtube.com/vi/%s/0.jpg";
    private static final String BACKDROP_IMAGE_URL = "http://image.tmdb.org/t/p/w780/";
    private static final String POSTER_IMAGE_URL = "http://image.tmdb.org/t/p/w185/";


    private static final String LOG_TAG = "MOVIE DETAIL ACTIVITY ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if (getIntent() != null) {
            Intent i = getIntent();
            mMovieId = getIntent().getExtras().getInt("EXTRA");
            mMovieName = getIntent().getExtras().getString("EXTRA_NAME");
            mGenreItem = (GenreItem) i.getSerializableExtra("EXTRA_GENRE");
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(mMovieName);
        mPresenter = new MovieDetailPresenter();
        mPresenter.attachView(this, mMovieId);

        viewHolder();
    }

    private void viewHolder() {
        mImageViewAppBar = findViewById(R.id.imageViewCollapsing);
        mTextViewReleaseDate = findViewById(R.id.listview_item_release_date);
        mTextViewLanguage = findViewById(R.id.listview_item_language);
        mTextViewGenre = findViewById(R.id.listview_item_genre);
        mTextViewRating = findViewById(R.id.listview_item_rating);
        mImageViewPoster = findViewById(R.id.listview_item_image);
        mTextViewOverview = findViewById(R.id.movie_detail_overview);
        mTextViewTrailer = findViewById(R.id.trailers_label);
        mLinearLayoutTrailer = findViewById(R.id.movie_trailers);
        mLinearLayoutReview = findViewById(R.id.movie_review_layout);
        mTrailerCardView = findViewById(R.id.movie_trailer_card);
        mReviewCardView = findViewById(R.id.movie_review_card);

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
            mTrailerCardView.setVisibility(View.VISIBLE);
            mTextViewTrailer.setVisibility(View.VISIBLE);
            mLinearLayoutTrailer.removeAllViews();
            for (final ResultTrailerItem item : data.getResults()) {
                View parent = getLayoutInflater().inflate(R.layout.movie_trailer_thumbnail, mLinearLayoutTrailer, false);
                ImageView thumbnail = parent.findViewById(R.id.trailer_imageview);
                thumbnail.requestLayout();
                thumbnail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = String.format(YOUTUBE_URL, item.getKey());
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    }
                });

                if (!isFinishing()) {
                    String value = String.format(YOUTUBE_THUMBNAIL_URL, item.getKey());
                    Picasso.get()
                            .load(value)
                            .fit()
                            .centerCrop()
                            .into(thumbnail);
                }
                mLinearLayoutTrailer.addView(parent);
            }
        } else {
            mTrailerCardView.setVisibility(View.GONE);
            mTextViewTrailer.setVisibility(View.GONE);
        }
    }

    @Override
    public void onTrailerListFailure(String errorMessage) {

    }

    @Override
    public void onMovieDetail(HeaderItem data, int movieId) {
        Log.i("MOVIE ID: ", movieId + " - ID");
        if (!data.getResults().isEmpty()) {
            for (int i = 0; i < data.getResults().size(); i++) {
                if (movieId == data.getResults().get(i).getId()) {

                    mHeaderItem = data.getResults().get(i);

                    String movieReleaseDate = mHeaderItem.getReleaseDate();
                    String movieLanguage = mHeaderItem.getOriginalLanguage();
                    double movieRating = mHeaderItem.getVoteAverage();
                    String movieOverview = mHeaderItem.getDescription();

                    StringBuilder builder = new StringBuilder();
                    String imageBackDropString = builder.append(BACKDROP_IMAGE_URL).append(mHeaderItem.getBackdropPath()).toString();

                    builder = new StringBuilder();
                    String imagePosterString = builder.append(POSTER_IMAGE_URL).append(mHeaderItem.getPoster()).toString();

                    Picasso.get().load(imageBackDropString).into(mImageViewAppBar);
                    Picasso.get().load(imagePosterString).into(mImageViewPoster);
                    mTextViewReleaseDate.setText(movieReleaseDate);
                    mTextViewLanguage.setText(movieLanguage);
                    mTextViewGenre.setText(genreId());
                    mTextViewRating.setText(String.valueOf(movieRating));
                    mTextViewOverview.setText(movieOverview);
                } else {
                    Log.i(LOG_TAG, "SOMETHING WENT WRONG. COULDN'T RECEIVE REVIEWS");
                }
            }
        } else {
            Log.i(LOG_TAG, "SOMETHING WENT WRONG. COULDN'T RECEIVE REVIEWS");
        }
    }


    @Override
    public void onReviewDetail(Reviews data) {
        if (!data.getResults().isEmpty()) {
            mReviewCardView.setVisibility(View.VISIBLE);
            for (ReviewResponse response : data.getResults()) {

                View parent = getLayoutInflater().inflate(R.layout.movie_review_details, mLinearLayoutReview, false);

                TextView tvAuthor = parent.findViewById(R.id.movie_review_author);
                final TextView tvContent = parent.findViewById(R.id.movie_review_content);

                tvContent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isContentClicked) {
                            tvContent.setMaxLines(2);
                            isContentClicked = false;
                        } else {
                            tvContent.setMaxLines(Integer.MAX_VALUE);
                            isContentClicked = true;
                        }
                    }
                });
                tvAuthor.setText("-- " + response.getAuthor());
                tvContent.setText(response.getContent());
                mLinearLayoutReview.addView(parent);
            }
        } else {
            mReviewCardView.setVisibility(View.GONE);
            Log.i(LOG_TAG, "SOMETHING WENT WRONG. COULDN'T RECEIVE REVIEWS");
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

    @Override
    public void onNowPlayingMovie(HeaderItem data, int movieId) {
        Log.i("MOVIE ID: ", movieId + " - ID");
        if (!data.getResults().isEmpty()) {
            for (int i = 0; i < data.getResults().size(); i++) {
                if (movieId == data.getResults().get(i).getId()) {

                    mHeaderItem = data.getResults().get(i);

                    String movieReleaseDate = mHeaderItem.getReleaseDate();
                    String movieLanguage = mHeaderItem.getOriginalLanguage();
                    double movieRating = mHeaderItem.getVoteAverage();
                    String movieOverview = mHeaderItem.getDescription();

                    StringBuilder builder = new StringBuilder();
                    String imageBackDropString = builder.append(BACKDROP_IMAGE_URL).append(mHeaderItem.getBackdropPath()).toString();

                    builder = new StringBuilder();
                    String imagePosterString = builder.append(POSTER_IMAGE_URL).append(mHeaderItem.getPoster()).toString();

                    Picasso.get().load(imageBackDropString).into(mImageViewAppBar);
                    Picasso.get().load(imagePosterString).into(mImageViewPoster);
                    mTextViewReleaseDate.setText(movieReleaseDate);
                    mTextViewLanguage.setText(movieLanguage);
                    mTextViewGenre.setText(genreId());
                    mTextViewRating.setText(String.valueOf(movieRating));
                    mTextViewOverview.setText(movieOverview);
                } else {
                    Log.i(LOG_TAG, "SOMETHING WENT WRONG. COULDN'T RECEIVE REVIEWS");
                }
            }
        } else {
            Log.i(LOG_TAG, "SOMETHING WENT WRONG. COULDN'T RECEIVE REVIEWS");
        }
    }

    @Override
    public void onTopRatedMovie(HeaderItem data, int movieId) {
        if (!data.getResults().isEmpty()) {
            for (int i = 0; i < data.getResults().size(); i++) {
                if (movieId == data.getResults().get(i).getId()) {

                    mHeaderItem = data.getResults().get(i);

                    String movieReleaseDate = mHeaderItem.getReleaseDate();
                    String movieLanguage = mHeaderItem.getOriginalLanguage();
                    double movieRating = mHeaderItem.getVoteAverage();
                    String movieOverview = mHeaderItem.getDescription();

                    StringBuilder builder = new StringBuilder();
                    String imageBackDropString = builder.append(BACKDROP_IMAGE_URL).append(mHeaderItem.getBackdropPath()).toString();

                    builder = new StringBuilder();
                    String imagePosterString = builder.append(POSTER_IMAGE_URL).append(mHeaderItem.getPoster()).toString();

                    Picasso.get().load(imageBackDropString).into(mImageViewAppBar);
                    Picasso.get().load(imagePosterString).into(mImageViewPoster);
                    mTextViewReleaseDate.setText(movieReleaseDate);
                    mTextViewLanguage.setText(movieLanguage);
                    mTextViewGenre.setText(genreId());
                    mTextViewRating.setText(String.valueOf(movieRating));
                    mTextViewOverview.setText(movieOverview);
                } else {
                    Log.i(LOG_TAG, "SOMETHING WENT WRONG. COULDN'T RECEIVE REVIEWS");
                }
            }
        } else {
            Log.i(LOG_TAG, "SOMETHING WENT WRONG. COULDN'T RECEIVE REVIEWS");
        }
    }
}
