package com.example.chirag.moviedb.moviedetail;

import android.content.Context;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import android.os.Bundle;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chirag.moviedb.R;

import com.example.chirag.moviedb.data.model.TMDB;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.data.model.Season;
import com.example.chirag.moviedb.data.model.TrailerResponse;
import com.example.chirag.moviedb.data.model.ReviewResponse;
import com.example.chirag.moviedb.data.model.Reviews;
import com.example.chirag.moviedb.data.model.Trailer;

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

    boolean appbarExpanded;

    private Menu collapsedMenu;

    private TMDB mTMDBInfo;

    private String mEmailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        viewHolder();

        Logger.addLogAdapter(new AndroidLogAdapter());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Item added to Favourites", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                insertTMDBInfo();
            }
        });

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
            if (getIntent().hasExtra("EXTRA_EMAIL")) {
                mEmailAddress = getIntent().getExtras().getString("EXTRA_EMAIL");
            }
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(mMovieName);

        mPresenter = new MovieDetailPresenter(getApplicationContext(), isConnected);
        mPresenter.attachView(this, mMovieId);

        AppBarLayout appBarLayout = findViewById(R.id.appBarLayout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (Math.abs(i) > 200) {
                    appbarExpanded = false;
                    invalidateOptionsMenu();
                } else {
                    appbarExpanded = true;
                    invalidateOptionsMenu();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_favourite:
                Toast.makeText(this, "Added to Favourite", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_detail, menu);
        collapsedMenu = menu;
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (collapsedMenu != null && (!appbarExpanded || collapsedMenu.size() != 1)) {
            collapsedMenu.findItem(R.id.action_favourite).setVisible(true);
        } else {
            collapsedMenu.findItem(R.id.action_favourite).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
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
    public void getMovieInfoHome(int movieId, TMDB data) {
        if (mContentType.equals(CONTENT_MOVIE)) {
            if (data != null) {
                mTMDBInfo = data;
                if (movieId == data.getId()) {
                    String movieReleaseDate = data.getReleaseDate();
                    String movieLanguage = data.getOriginalLanguage();
                    double movieRating = data.getVoteAverage();
                    String movieOverview = data.getOverview();
                    String movieGenre;

                    if (isConnected) {
                        movieGenre = data.getGenresDetail();
                    } else {
                        movieGenre = data.getGenreInfo();
                    }

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
    public void getTvInfoHome(int tvId, TMDB data) {
        if (mContentType.equals(CONTENT_TV)) {
            if (data != null) {
                if (tvId == data.getId()) {
                    String tvReleaseDate = data.getFirstAirDate();
                    String tvLanguage = data.getOriginalLanguage();
                    double tvRating = data.getVoteAverage();
                    String tvOverview = data.getOverview();
                    String tvGenre;

                    if (isConnected) {
                        tvGenre = data.getGenresDetail();
                    } else {
                        tvGenre = data.getGenreInfo();
                    }

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
                    mTextViewGenre.setText(tvGenre);
                    mTextViewRating.setText(String.valueOf(tvRating));
                    mTextViewOverview.setText(tvOverview);

                    mTextViewSimilarLabel.setText("Season Information");
                    if (isConnected) {
                        for (final Season item : data.getSeasons()) {
                            View parent = getLayoutInflater().inflate(R.layout.movie_home_poster, mLinearLayoutSimilarMovies, false);
                            ImageView poster = parent.findViewById(R.id.movie_home_imageview);
                            String imageSeasonPosterString = POSTER_IMAGE_URL + item.getPosterPath();
                            Picasso.get().load(imageSeasonPosterString).into(poster);
                            mLinearLayoutSimilarMovies.addView(parent);
                        }
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
                    String imagePosterString = POSTER_IMAGE_URL + item.getPoster();
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

    @Override
    public void insertTMDBInfo() {
        mTMDBInfo.setUserEmail(mEmailAddress);
        mPresenter.insertTMDB(mTMDBInfo);
    }

    private void startNewActivity(int movieId, String movieName) {
        Intent intent = new Intent(getApplicationContext(), MovieDetailActivity.class);
        intent.putExtra(EXTRA_ID, movieId);
        intent.putExtra(EXTRA_TITLE, movieName);
        intent.putExtra(CONTENT_TYPE, CONTENT_MOVIE);
        startActivity(intent);
    }
}
