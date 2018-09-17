package com.example.chirag.moviedb;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chirag.moviedb.model.GenreItem;
import com.example.chirag.moviedb.model.HeaderItem;
import com.squareup.picasso.Picasso;

/**
 * MovieDB
 * Created by Chirag on 18/08/18.
 */
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private HeaderItem mListDataHeader;
    private GenreItem mGenreItem;

    ExpandableListViewAdapter(Context mContext, HeaderItem mListDataHeader, GenreItem response) {
        this.mContext = mContext;
        this.mListDataHeader = mListDataHeader;
        this.mGenreItem = response;
    }

    @Override
    public int getGroupCount() {
        return mListDataHeader.getResults().size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return mListDataHeader.getResults().get(i);
    }

    @Override
    public Object getChild(int i, int j) {
        return mListDataHeader.getResults().get(i);

    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int j) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headerTitle = mListDataHeader.getResults().get(i).getTitle();
        String headerYear = mListDataHeader.getResults().get(i).getReleaseDate();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.expandable_list_group, null);
        }

        TextView tvHeader = view.findViewById(R.id.listview_header);
        tvHeader.setTypeface(null, Typeface.BOLD);
        tvHeader.setText(headerTitle);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        StringBuilder image = new StringBuilder();

        String headerChildImage = mListDataHeader.getResults().get(i).getPoster();
        image.append("http://image.tmdb.org/t/p/w185/").append(headerChildImage);
        String image1 = image.toString();

        String headerChildCast = mListDataHeader.getResults().get(i).getReleaseDate();
        String headerChildDirector = mListDataHeader.getResults().get(i).getOriginalLanguage();

        double rating = mListDataHeader.getResults().get(i).getVoteAverage();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.expandable_list_item, null);
        }

        TextView tvChildList = view.findViewById(R.id.listview_item_genre);
        ImageView ivChildList = view.findViewById(R.id.listview_item_image);
        TextView tvChildItemCast = view.findViewById(R.id.listview_item_release_date);
        TextView tvChildItemDirector = view.findViewById(R.id.listview_item_language);
        TextView tvItemRating = view.findViewById(R.id.listview_item_rating);

        tvChildItemCast.setText(headerChildCast);
        tvChildItemDirector.setText(headerChildDirector);
        tvChildList.setText(genreId(i));
        Picasso.get().load(image1).into(ivChildList);
        tvItemRating.setText(String.valueOf(rating) + "/10");
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    private String genreId(int i) {
        StringBuilder genre = new StringBuilder();
        int size = mGenreItem.getResultGenreItems().size();

        for (int j = 0; j < size; j++) {
            if (mGenreItem.getResultGenreItems().get(j).getId().equals(mListDataHeader.getResults().get(i).getGenreId().get(0))) {
                genre.append(mGenreItem.getResultGenreItems().get(j).getName());
            }
        }
        for (int j1 = 0; j1 < size; j1++) {
            for (int k = 1; k < mListDataHeader.getResults().get(i).getGenreId().size(); k++) {
                if (mListDataHeader.getResults().get(i).getGenreId().get(k).equals(mGenreItem.getResultGenreItems().get(j1).getId())) {
                    genre.append(", ");
                    genre.append(mGenreItem.getResultGenreItems().get(j1).getName());
                }
            }
        }

        return genre.toString();
    }
}
