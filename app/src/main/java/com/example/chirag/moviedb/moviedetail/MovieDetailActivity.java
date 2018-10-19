package com.example.chirag.moviedb.moviedetail;

import android.content.Context;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chirag.moviedb.R;

import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.data.model.MovieInfo;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.data.model.Season;
import com.example.chirag.moviedb.data.model.TrailerResponse;
import com.example.chirag.moviedb.data.model.ReviewResponse;
import com.example.chirag.moviedb.data.model.Reviews;
import com.example.chirag.moviedb.data.model.Trailer;

import com.example.chirag.moviedb.data.model.TvInfo;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import com.squareup.picasso.Picasso;

import static com.example.chirag.moviedb.data.Constant.BACKDROP_IMAGE_URL;
import static com.example.chirag.moviedb.data.Constant.CONTENT_MOVIE;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TV;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE;
import static com.example.chirag.moviedb.data.Constant.EXTRA_ID;
import static com.example.chirag.moviedb.data.Constant.EXTRA_TITLE;
import static com.example.chirag.moviedb.data.Constant.POSTER_IMAGE_URL;
import static com.example.chirag.moviedb.data.Constant.YOUTUBE_THUMBNAIL_URL;
import static com.example.chirag.moviedb.data.Constant.YOUTUBE_URL;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailContract.View {

    ImageView mImageViewAppBar;
    ImageView mImageViewPoster;

    TextView mTextViewReleaseDate;
    TextView mTextViewLanguage;
    TextView mTextViewGenre;
    TextView mTextViewRating;
    TextView mTextViewOverview;
    TextView mTextViewTrailer;
    TextView mTextViewReleaseDateLabel;
    TextView mTextViewSimilarLabel;
    TextView mTextViewReview;

    LinearLayout mLinearLayoutTrailer;
    LinearLayout mLinearLayoutReview;

    MovieDetailPresenter mPresenter;

    LinearLayout mLinearLayoutSimilarMovies;

    CardView mTrailerCardView;
    CardView mReviewCardView;

    int mMovieId;
    String mMovieName;
    boolean isConnected;
    String mContentType;
    boolean isContentClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        viewHolder();

        Logger.addLogAdapter(new AndroidLogAdapter());

        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        if (getIntent() != null) {
            if (getIntent().hasExtra(EXTRA_ID)) {
                mMovieId = getIntent().getExtras().getInt(EXTRA_ID);
            }
            if (getIntent().hasExtra(EXTRA_TITLE)) {
                mMovieName = getIntent().getExtras().getString(EXTRA_TITLE);
            }
            if (getIntent().hasExtra(CONTENT_TYPE)) {
                mContentType = getIntent().getExtras().getString(CONTENT_TYPE);
            }
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(mMovieName);

        mPresenter = new MovieDetailPresenter(getApplicationContext(), isConnected);
        mPresenter.attachView(this, mMovieId);
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
        mLinearLayoutSimilarMovies = findViewById(R.id.movie_similar);
        mTextViewReleaseDateLabel = findViewById(R.id.release_date_label);
        mTextViewSimilarLabel = findViewById(R.id.similar_label);
        mTextViewReview = findViewById(R.id.movie_review_label);
    }

    @Override
    public void getMovieInfoHome(int movieId, MovieInfo data) {
        if (mContentType.equals(CONTENT_MOVIE)) {
            if (data != null) {
                if (movieId == data.getId()) {
                    String movieReleaseDate = data.getReleaseDate();
                    String movieLanguage = data.getOriginalLanguage();
                    double movieRating = data.getVoteAverage();
                    String movieOverview = data.getOverview();
                    String movieGenre;

                    StringBuilder builder = new StringBuilder();
                    String imageBackDropString = builder.append(BACKDROP_IMAGE_URL).append(data.getBackdropPath()).toString();

                    builder = new StringBuilder();
                    String imagePosterString = builder.append(POSTER_IMAGE_URL).append(data.getPosterPath()).toString();

                    Picasso.get().load(imageBackDropString).into(mImageViewAppBar);
                    Picasso.get().load(imagePosterString).into(mImageViewPoster);
                    if (movieReleaseDate == null) {
                        mTextViewReleaseDateLabel.setVisibility(View.GONE);
                        mTextViewReleaseDate.setVisibility(View.GONE);
                    } else {
                        mTextViewReleaseDateLabel.setVisibility(View.VISIBLE);
                        mTextViewReleaseDate.setVisibility(View.VISIBLE);
                        mTextViewReleaseDate.setText(movieReleaseDate);
                    }
                    mTextViewLanguage.setText(movieLanguage);
                    if (isConnected) {
                        movieGenre = data.getGenreInfo();
                    } else {
                        movieGenre = "asdasd";
                    }
                    mTextViewGenre.setText(movieGenre);
                    mTextViewRating.setText(String.valueOf(movieRating));
                    mTextViewOverview.setText(movieOverview);
                } else {
                    Logger.i(getString(R.string.movie_error));
                }
            }
        }
    }

    @Override
    public void getTvInfoHome(int tvId, TvInfo data) {
        if (mContentType.equals(CONTENT_TV)) {
            if (data != null) {
                if (tvId == data.getId()) {
                    String tvReleaseDate = data.getFirstAirDate();
                    String tvLanguage = data.getOriginalLanguage();
                    double tvRating = data.getVoteAverage();
                    String tvOverview = data.getOverview();
                    String tvGenre;

                    StringBuilder builder = new StringBuilder();
                    String imageBackDropString = builder.append(BACKDROP_IMAGE_URL).append(data.getBackdropPath()).toString();

                    builder = new StringBuilder();
                    String imagePosterString = builder.append(POSTER_IMAGE_URL).append(data.getPosterPath()).toString();

                    Picasso.get().load(imageBackDropString).into(mImageViewAppBar);
                    Picasso.get().load(imagePosterString).into(mImageViewPoster);
                    if (tvReleaseDate == null) {
                        mTextViewReleaseDateLabel.setVisibility(View.GONE);
                        mTextViewReleaseDate.setVisibility(View.GONE);
                    } else {
                        mTextViewReleaseDateLabel.setVisibility(View.VISIBLE);
                        mTextViewReleaseDate.setVisibility(View.VISIBLE);
                        mTextViewReleaseDate.setText(tvReleaseDate);
                    }
                    mTextViewLanguage.setText(tvLanguage);
                    if (isConnected) {
                        tvGenre = data.getGenreInfo();
                    } else {
                        tvGenre = "asdasd";
                    }
                    mTextViewGenre.setText(tvGenre);
                    mTextViewRating.setText(String.valueOf(tvRating));
                    mTextViewOverview.setText(tvOverview);

                    mTextViewSimilarLabel.setText("Tv Season Information");
                    for (final Season item : data.getSeasons()) {
                        View parent = getLayoutInflater().inflate(R.layout.movie_home_poster, mLinearLayoutSimilarMovies, false);
                        ImageView poster = parent.findViewById(R.id.movie_home_imageview);
                        StringBuilder seasonBuilder = new StringBuilder();
                        String imageSeasonPosterString = seasonBuilder.append(POSTER_IMAGE_URL).append(item.getPosterPath()).toString();
                        Picasso.get().load(imageSeasonPosterString).into(poster);
                        mLinearLayoutSimilarMovies.addView(parent);
                    }
                } else {
                    Logger.i(getString(R.string.movie_error));
                }
            }
        }
    }

    @Override
    public void getTrailerDetail(Trailer data) {
        if (mContentType.equals(CONTENT_MOVIE)) {
            if (!data.getResults().isEmpty()) {
                mTrailerCardView.setVisibility(View.VISIBLE);
                mTextViewTrailer.setVisibility(View.VISIBLE);
                mLinearLayoutTrailer.removeAllViews();
                for (final TrailerResponse item : data.getResults()) {
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
        } else {
            mTrailerCardView.setVisibility(View.GONE);
            mTextViewTrailer.setVisibility(View.GONE);
        }
    }

    @Override
    public void getResultFailure(String errorMessage) {

    }


    @Override
    public void getReviewDetail(Reviews data) {
        if (mContentType.equals(CONTENT_MOVIE)) {
            if (!data.getResults().isEmpty()) {
                mReviewCardView.setVisibility(View.VISIBLE);
                mTextViewReview.setVisibility(View.VISIBLE);
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
                mTextViewReview.setVisibility(View.GONE);
                Logger.i(getString(R.string.review_error));
            }
        } else {
            mReviewCardView.setVisibility(View.GONE);
            mTextViewReview.setVisibility(View.GONE);
        }
    }

    @Override
    public void getSimilarMovieDetail(Result data, int movieId) {
        if (mContentType.equals(CONTENT_MOVIE)) {
            if (data != null && data.getResults() != null) {
                mTrailerCardView.setVisibility(View.VISIBLE);
                mTextViewTrailer.setVisibility(View.VISIBLE);
                for (final ResultResponse item : data.getResults()) {
                    View parent = getLayoutInflater().inflate(R.layout.movie_home_poster, mLinearLayoutSimilarMovies, false);
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
                    mLinearLayoutSimilarMovies.addView(parent);
                }
            } else {
                mTrailerCardView.setVisibility(View.GONE);
                mTextViewTrailer.setVisibility(View.GONE);
            }
        }
    }

    private void startNewActivity(int movieId, String movieName) {
        Intent intent = new Intent(getApplicationContext(), MovieDetailActivity.class);
        intent.putExtra(EXTRA_ID, movieId);
        intent.putExtra(EXTRA_TITLE, movieName);
        intent.putExtra(CONTENT_TYPE, CONTENT_MOVIE);
        startActivity(intent);
    }
}
