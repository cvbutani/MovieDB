package com.example.chirag.moviedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.chirag.moviedb.model.ResultHeaderItem;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent i = getIntent();
        ResultHeaderItem item = (ResultHeaderItem) i.getSerializableExtra("EXTRA");
        ImageView image = findViewById(R.id.imageViewCollapsing);
        StringBuilder builder = new StringBuilder();
        String headerChildImage = item.getBackdropPath();
        builder.append("http://image.tmdb.org/t/p/w780/").append(headerChildImage);
        String image1 = builder.toString();

        Picasso.get().load(image1).into(image);

    }
}
