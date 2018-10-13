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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chirag.moviedb.R;
import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.data.model.Movies;
import com.example.chirag.moviedb.data.model.MovieResponse;
import com.example.chirag.moviedb.data.model.TrailerResponse;
import com.example.chirag.moviedb.data.model.ReviewResponse;
import com.example.chirag.moviedb.data.model.Reviews;
import com.example.chirag.moviedb.data.model.Trailer;
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

    MovieResponse mHeaderItem;
    Genre mGenreItem;
    MovieDetailPresenter mPresenter;

    LinearLayout mLinearLayoutSimilarMovies;

    CardView mTrailerCardView;
    CardView mReviewCardView;

    private int mMovieId;
    private String mMovieName;
    boolean isConnected;

    private boolean isContentClicked = false;

    private static final String YOUTUBE_URL = "http://www.youtube.com/watch?v=%s";
    private static final String YOUTUBE_THUMBNAIL_URL = "http://img.youtube.com/vi/%s/0.jpg";
    private static final String BACKDROP_IMAGE_URL = "http://image.tmdb.org/t/p/w780/";
    private static final String POSTER_IMAGE_URL = "http://image.tmdb.org/t/p/w185/";

    private Genre mGenreItems;

    private static final String LOG_TAG = "MOVIE DETAIL ACTIVITY ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        viewHolder();

        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        if (getIntent() != null) {
            Intent i = getIntent();
            mMovieId = getIntent().getExtras().getInt("EXTRA");
            mMovieName = getIntent().getExtras().getString("EXTRA_NAME");
            mGenreItem = (Genre) i.getSerializableExtra("EXTRA_GENRE");
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
    }

    private String genreId() {
        StringBuilder genre = new StringBuilder();
        int size = mGenreItem.getGenreResponses().size();

        for (int j = 0; j < size; j++) {
            if (mGenreItem.getGenreResponses().get(j).getId().equals(mHeaderItem.getGenreIds().get(0))) {
                genre.append(mGenreItem.getGenreResponses().get(j).getName());
            }
        }
        for (int j1 = 0; j1 < size; j1++) {
            for (int k = 1; k < mHeaderItem.getGenreIds().size(); k++) {
                if (mHeaderItem.getGenreIds().get(k).equals(mGenreItem.getGenreResponses().get(j1).getId())) {
                    genre.append(", ");
                    genre.append(mGenreItem.getGenreResponses().get(j1).getName());
                }
            }
        }
        return genre.toString();
    }

    @Override
    public void onTrailerListSuccess(Trailer data) {
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
    }

    @Override
    public void onTrailerListFailure(String errorMessage) {

    }

    @Override
    public void onMovieDetail(Movies data, int movieId) {
        movieData(data, movieId);
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
    public void onGenreDetail(Genre data) {
        mGenreItems = data;
    }

    @Override
    public void onNowPlayingMovie(Movies data, int movieId) {
        movieData(data, movieId);
    }

    @Override
    public void onTopRatedMovie(Movies data, int movieId) {
        movieData(data, movieId);
    }

    @Override
    public void onUpcomingMovie(Movies data, int movieId) {
        movieData(data, movieId);
    }

    @Override
    public void onSimilarMovieSuccess(Movies data, int movieId) {
        for (final MovieResponse item : data.getResults()) {
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
    }

    @Override
    public void onSimilarMovieFailure(String errorMessage) {

    }

    private void movieData(Movies data, int movieId) {
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
//                    mTextViewGenre.setText("+Action");
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

    private void startNewActivity(int movieId, String name) {
        Intent intent = new Intent(MovieDetailActivity.this, MovieDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXTRA", movieId);
        intent.putExtra("EXTRA_NAME", name);
        intent.putExtra("EXTRA_GENRE", mGenreItems);
        startActivity(intent);
    }
}
