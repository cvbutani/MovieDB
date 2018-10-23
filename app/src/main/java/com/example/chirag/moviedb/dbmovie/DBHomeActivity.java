package com.example.chirag.moviedb.dbmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.chirag.moviedb.R;
import com.example.chirag.moviedb.dbtv.DbTvFragment;
import com.example.chirag.moviedb.favourite.FavouriteFragment;
import com.example.chirag.moviedb.user.account.UserAccountActivity;
import com.example.chirag.moviedb.user.login.LoginActivity;

public class DBHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String mUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbhome);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent().hasExtra("EXTRA_EMAIL")) {
            mUserEmail = getIntent().getStringExtra("EXTRA_EMAIL");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        onNavigationItemSelected(navigationView.getMenu().getItem(0).setChecked(true));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }
        super.onBackPressed();
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
        if (id == R.id.action_user) {
            Intent intent = new Intent(this, UserAccountActivity.class);
            intent.putExtra("EXTRA_EMAIL", mUserEmail);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_exit) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Bundle arg = new Bundle();
        arg.putString("EXTRA_EMAIL", mUserEmail);
        switch (id) {
            case R.id.nav_movie:
                DbHomeFragment fragment = new DbHomeFragment();
                fragment.setArguments(arg);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
                break;
            case R.id.nav_tv:
                DbTvFragment dbTvFragment = new DbTvFragment();
                dbTvFragment.setArguments(arg);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, dbTvFragment).commit();
                break;
            case R.id.nav_fav:
                FavouriteFragment favouriteFragment = new FavouriteFragment();
                favouriteFragment.setArguments(arg);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, favouriteFragment).commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

