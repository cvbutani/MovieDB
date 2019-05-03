package com.example.chirag.moviedb.util;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;

import java.util.concurrent.Executor;

/**
 * MovieDB
 * Created by Chirag on 24/09/18.
 */
public class MainThreadExecutor implements Executor {

    private Handler mMainThreadHandler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(@NonNull Runnable runnable) {
        mMainThreadHandler.post(runnable);
    }
}
