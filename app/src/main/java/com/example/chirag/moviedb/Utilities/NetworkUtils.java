package com.example.chirag.moviedb.Utilities;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * MovieDB
 * Created by Chirag on 19/08/18.
 */
public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    public NetworkUtils() {
    }

    /**
     * Creates URL object from the String value.
     *
     * @param findUrl - String value passed to generate URL object.
     * @return URL object.
     */
    public static URL getUrl(String findUrl) {
        URL url = null;

        try {
            url = new URL(findUrl);
        } catch (MalformedURLException e) {
            Log.i(TAG, "PROBLEM BUILDING URL" + e);
        }
        return url;
    }

    /**
     * Obtains new HttpUrlConnection by calling URL.openConnection() and casting result
     * to HttpURLConnection.
     * Prepares the request. The response body may be read from the stream returned by
     * URLConnection.getInputStream().
     * Once the response body has been read, the HttpURLConnection should be closed by
     * calling disconnect().
     *
     * @param url will be passed generated from createURL method.
     * @return response body.
     * @throws IOException - If the HTTP response indicates that an error occurred,
     *                     URLConnection.getInputStream() will throw an IOException.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.i(TAG, "ERROR RESPONSE CODE: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.i(TAG, "PROBLEM RETRIEVING NEWS INFO FROM GIVEN JSON RESULT" + e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Most callers should wrap the returned streams with BufferedInputStream or
     * BufferedOutputStream. Callers that do only bulk reads or writes may omit
     * buffering.
     * When transferring large amounts of data to or from a server, use streams
     * to limit how much data is in memory at once. Unless you need the entire
     * body to be in memory at once, process it as a stream
     *
     * @param inputStream created in makeHttpRequest method.
     * @return Response in String format.
     */
    private static String readFromStream(InputStream inputStream) {
        StringBuilder output = new StringBuilder();

        try {
            if (inputStream != null) {

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String inputLine = bufferedReader.readLine();

                while (inputLine != null) {
                    output.append(inputLine);
                    inputLine = bufferedReader.readLine();
                }
            }
        } catch (IOException e) {
            Log.i(TAG, "CAN'T READ ANYTHING FROM INPUT STREAM" + e);
        }
        return output.toString();
    }


}
