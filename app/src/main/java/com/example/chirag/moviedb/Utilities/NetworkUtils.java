package com.example.chirag.moviedb.Utilities;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.chirag.moviedb.Data.ChildItems;
import com.example.chirag.moviedb.Data.HeaderItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 19/08/18.
 */
public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static StringBuilder poster;

    public NetworkUtils() {
    }

    public static List<HeaderItems> fetchHeaderItems(String requestUrl) {
        URL url = getUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.i(TAG, "PROBLEM MAKING HTTP REQUEST" + e);
        }
        return extractHeaderInfo(jsonResponse);
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

    private static List<HeaderItems> extractHeaderInfo(String headerJson) {
        if (TextUtils.isEmpty(headerJson)) {
            return null;
        }
        List<HeaderItems> headerDetails = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(headerJson);
            JSONArray resultsArray = null;

            if (jsonObject.has("results")) {
                resultsArray = jsonObject.getJSONArray("results");
            }

            if (resultsArray != null) {
                for (int i = 0; i < resultsArray.length(); i++) {
                    JSONObject elementsInItem = resultsArray.getJSONObject(i);
                    //  MOVIE TITLE
                    String title = extractValue(elementsInItem, "title");
                    //  MOVIE ID
                    int id = Integer.parseInt(extractValue(elementsInItem, "id"));
                    //  MOVIE RATING
                    float rating = Float.parseFloat(extractValue(elementsInItem, "vote_average"));

                    HeaderItems items = new HeaderItems(id, title, rating);
                    headerDetails.add(items);
                }
            }
        } catch (JSONException e) {
            Log.i(TAG, "LOG PARSING DATA FROM JSON", e);
        }
        return headerDetails;
    }

    private static String extractValue(JSONObject object, String key) throws JSONException {
        String value = "";
        if (object.has(key)) {
            value = object.getString(key);
        }
        return value;
    }

    private static List<HeaderItems> extractChildInfo(String childJson) {
        if (TextUtils.isEmpty(childJson)) {
            return null;
        }
        List<HeaderItems> headerDetails = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(childJson);
            String overview = "";
            if (jsonObject.has("overview")) {
                overview = jsonObject.getString("overview");
            }
            poster = new StringBuilder();


            if (jsonObject.has("poster_path")) {
                poster.append("http://image.tmdb.org/t/p/w185/");
                poster.append(jsonObject.getString("poster_path"));
            }
            JSONArray crewArray = null;
            if (jsonObject.has("crew")) {
                crewArray = jsonObject.getJSONArray("crew");
            }
            String constant_director = "Director";
            String director = "";
            StringBuilder allDirector = new StringBuilder();
            if (crewArray != null) {
                for (int i = 0; i < crewArray.length(); i++) {
                    JSONObject crewElement = crewArray.getJSONObject(i);
                    if (crewElement.has("job")) {
                        director = crewElement.getString("job");
                        if (director.equals(constant_director)) {
                            if (crewElement.has("name")) {
                                allDirector.append(crewElement.getString("name")).append(", ");
                            }
                        }
                    }
                }
            }
            Log.i(TAG, overview);
            Log.i(TAG, poster + " --------- " + allDirector.toString());

        } catch (JSONException e) {
            Log.i(TAG, "LOG PARSING DATA FROM JSON", e);
        }
        return headerDetails;
    }

}
