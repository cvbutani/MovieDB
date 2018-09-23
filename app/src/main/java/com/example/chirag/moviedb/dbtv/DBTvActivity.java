package com.example.chirag.moviedb.dbtv;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.chirag.moviedb.model.HeaderItem;

/**
 * MovieDB
 * Created by Chirag on 23/09/18.
 */
public class DBTvActivity extends AppCompatActivity implements DBTvContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DBTvPresenter presenter = new DBTvPresenter();
        presenter.attchView(this);
    }

    @Override
    public void onPopularTvSuccess(HeaderItem data) {
        Log.i("MOVIE TITLE ", data.getResults().get(0).getOriginalName());
    }

    @Override
    public void onPopularTvFailure(String errorMessage) {

    }
}
