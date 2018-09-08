package com.example.chirag.moviedb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.example.chirag.moviedb.Constant.PublicKeys;
import com.example.chirag.moviedb.Constant.UriBuilder;
import com.example.chirag.moviedb.data.ChildItems;
import com.example.chirag.moviedb.data.HeaderItems;
import com.example.chirag.moviedb.Utilities.NetworkUtils;
import com.example.chirag.moviedb.model.childitem.ChildItem;
import com.example.chirag.moviedb.model.headeritem.HeaderItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DbHomeContract.View {

    private ExpandableListView mExpandableListView;
    private ExpandableListViewAdapter mExpandableListViewAdapter;
    private List<HeaderItems> mViewheader;
    private HashMap<String, List<ChildItems>> mHeaderTitle;
    private int lastExpandedPosition = -1;
    private UriBuilder mUriBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbhome);

        DbHomePresenter presenter = new DbHomePresenter();
        presenter.attachView(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initData();
        mExpandableListView = findViewById(R.id.expandable_listview);
        mExpandableListViewAdapter = new ExpandableListViewAdapter(this, mViewheader, mHeaderTitle);
        mExpandableListView.setAdapter(mExpandableListViewAdapter);

        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int i) {
                if (lastExpandedPosition != -1 && i != lastExpandedPosition) {
                    mExpandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = i;
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dbhome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            mUriBuilder = new UriBuilder();
            final String finalUrl = mUriBuilder.headerUriBuilder(PublicKeys.POPULAR);

            Log.i(this.getClass().getSimpleName().toUpperCase(), finalUrl);
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    mViewheader = NetworkUtils.fetchHeaderItems(finalUrl);
                }
            });
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //
    private void initData() {

//        mHeaderTitle = new HashMap<>();
        mViewheader = new ArrayList<>();
        HeaderItems items = new HeaderItems(123456, "Death Wish", 4.5f);
        mViewheader.add(items);
//        mViewheader.add(items);
//
//        String deathWish = "Dr. Paul Kersey is an experienced trauma surgeon, a man who has spent his life saving lives. After an attack on his family, Paul embarks on his own mission for justice.";
//        String deathWishDirctor = "Eli Roth";
//        String deathWishCast = "Bruce Willis, Vincent D'Onofrio, Elisabeth Shue";
//
//        ChildItems childItems = new ChildItems(deathWish, R.drawable.deathwish, deathWishDirctor, deathWishCast);
//        List<ChildItems> itemsList = new ArrayList<>();
//        itemsList.add(childItems);
//        List<ChildItems> itemsList1 = new ArrayList<>();
//        itemsList1.add(childItems);
//
////          List<String> theNun = new ArrayList<>();
////        theNun.add("A priest with a haunted past and a novice on the threshold of her final vows are sent by the Vatican to investigate the death of a young nun in Romania and confront a malevolent force in the form of a demonic nun.");
////
////        List<String> jumanji = new ArrayList<>();
////        jumanji.add("Four teenagers are sucked into a magical video game, and the only way they can escape is to work together to finish the game.");
////
////        List<String> babyDayOut = new ArrayList<>();
////        babyDayOut.add("Baby Bink couldn't ask for more; he has adoring (if somewhat sickly-sweet) parents, he lives in a huge mansion, and he's just about to appear in the social pages of the paper.");
//
//        mHeaderTitle.put(mViewheader.get(0).getTitle(), itemsList);
//        mHeaderTitle.put(mViewheader.get(1).getTitle(), itemsList1);
    }

    @Override
    protected void onResume() {
        mExpandableListViewAdapter = new ExpandableListViewAdapter(this, mViewheader, mHeaderTitle);
        mExpandableListView.setAdapter(mExpandableListViewAdapter);

        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int i) {
                if (lastExpandedPosition != -1 && i != lastExpandedPosition) {
                    mExpandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = i;
            }
        });

        super.onResume();
    }

    @Override
    public void onHeaderResultSuccess(HeaderItem data) {
        Log.i("RESULT: ", data.getResults().get(0).getTitle());
    }

    @Override
    public void onHeaderResultFailure(String errorMessage) {
        Log.i("RESULT: ", "SOMETHING WENT WRONG" + errorMessage);
    }

    @Override
    public void onChildResultSuccess(ChildItem data) {
        Log.i("CHILD ITEM CAST ", data.getCast().get(0).getName());
        Log.i("CHILD ITEM CREW ", data.getCrew().get(0).getJob());
    }

    @Override
    public void onChildResultFailure(String errorMessage) {

    }
}

