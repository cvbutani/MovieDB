package com.example.chirag.moviedb.Constant;

import android.net.Uri;

/**
 * MovieDB
 * Created by Chirag on 19/08/18.
 */
public class UriBuilder {
    public UriBuilder() {
    }

    //   https://api.themoviedb.org/3/movie/popular?api_key=51b4547daeeca9a0a1dec36a7013b1ad&language=en-US&page=1
    //   https://api.themoviedb.org/3/movie/299536?api_key=51b4547daeeca9a0a1dec36a7013b1ad&language=en-US&page=1
    public String headerUriBuilder(String key) {
        Uri baseUri = Uri.parse(PublicKeys.SCHEME_PART);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendPath(key);
        uriBuilder.appendQueryParameter(PublicKeys.API_KEY_STRING, PublicKeys.API_KEY);
        uriBuilder.appendQueryParameter(PublicKeys.LANGUAGE, PublicKeys.LANGUAGE_EN);
        uriBuilder.appendQueryParameter(PublicKeys.PAGE, PublicKeys.PAGE_NUMBER);
        return uriBuilder.toString();
    }

    //    https://api.themoviedb.org/3/movie/299536/credits?api_key=51b4547daeeca9a0a1dec36a7013b1ad
    public String childUriBuilder(String id) {
        Uri baseUri = Uri.parse(PublicKeys.SCHEME_PART);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendPath(id);
        uriBuilder.appendPath(PublicKeys.CREDITS);
        uriBuilder.appendQueryParameter(PublicKeys.API_KEY_STRING, PublicKeys.API_KEY);
        return uriBuilder.toString();
    }
}
