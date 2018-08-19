package com.example.chirag.moviedb;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.chirag.moviedb.Data.ChildItems;
import com.example.chirag.moviedb.Data.HeaderItems;

import java.util.HashMap;
import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 18/08/18.
 */
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<HeaderItems> mListDataHeader;
    private HashMap<String, List<ChildItems>> mListHashMap;

    ExpandableListViewAdapter(Context mContext, List<HeaderItems> mListDataHeader, HashMap<String, List<ChildItems>> mListHashMap) {
        this.mContext = mContext;
        this.mListDataHeader = mListDataHeader;
        this.mListHashMap = mListHashMap;
    }

    @Override
    public int getGroupCount() {
        return mListDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mListHashMap.get(mListDataHeader.get(i).getTitle()).size();
    }

    @Override
    public Object getGroup(int i) {
        return mListDataHeader.get(i);
    }

    @Override
    public Object getChild(int i, int j) {
        Log.i("CHILD: ", "CHILD: " + j);
        return mListHashMap.get(mListDataHeader.get(i).getTitle()).size();

    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int j) {
        return j;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headerTitle = mListDataHeader.get(i).getTitle();
        float headerRating = mListDataHeader.get(i).getRating();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.expandable_list_group, null);
        }

        TextView tvHeader = view.findViewById(R.id.listview_header);
        RatingBar ratingHeader = view.findViewById(R.id.listview_header_rating);
        ratingHeader.setRating(headerRating);
        tvHeader.setTypeface(null, Typeface.BOLD);
        tvHeader.setText(headerTitle);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String headerChildText = mListHashMap.get(mListDataHeader.get(i).getTitle()).get(i1).getDescription();
        int headerChildImage = mListHashMap.get(mListDataHeader.get(i).getTitle()).get(i1).getImage();
        String headerChildCast = mListHashMap.get(mListDataHeader.get(i).getTitle()).get(i1).getCast();
        String headerChildDirector = mListHashMap.get(mListDataHeader.get(i).getTitle()).get(i1).getDirctor();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.expandable_list_item, null);
        }

        TextView tvChildList = view.findViewById(R.id.listview_item_description);
        ImageView ivChildList = view.findViewById(R.id.listview_item_image);
        TextView tvChildItemCast = view.findViewById(R.id.listview_item_cast);
        TextView tvChildItemDirector = view.findViewById(R.id.listview_item_director);

        tvChildItemCast.setText(headerChildCast);
        tvChildItemDirector.setText(headerChildDirector);
        tvChildList.setText(headerChildText);
        ivChildList.setImageResource(headerChildImage);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
