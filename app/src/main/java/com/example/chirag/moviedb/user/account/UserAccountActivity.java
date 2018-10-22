package com.example.chirag.moviedb.user.account;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chirag.moviedb.R;
import com.example.chirag.moviedb.data.model.User;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class UserAccountActivity extends AppCompatActivity implements UserAccountContract.View {

    String mUserEmail;

    User mUser;

    UserAccountPresenter mPresenter;

    EditText mFirstName;
    EditText mLastName;
    EditText mPassword;
    EditText mEmailAddress;

    Button mSaveUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_user);

        findAllView();
        if (getIntent().hasExtra("EXTRA_EMAIL")) {
            mUserEmail = getIntent().getStringExtra("EXTRA_EMAIL");
        }
        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.i(mUserEmail);

        mPresenter = new UserAccountPresenter(this, true);
        mPresenter.attachView(this, mUserEmail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSaveUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCurrentUser();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void findAllView() {
        mFirstName = findViewById(R.id.account_firstname);
        mLastName = findViewById(R.id.account_lastname);
        mPassword = findViewById(R.id.account_password);
        mEmailAddress = findViewById(R.id.account_email);
        mSaveUserButton = findViewById(R.id.account_save);
    }

    @Override
    public void saveCurrentUser() {
        String firstName = mFirstName.getText().toString();
        String lastName = mLastName.getText().toString();
        String password = mPassword.getText().toString();

        mUser.setPassWord(password);
        mUser.setLastName(lastName);
        mUser.setFirstName(firstName);
        mPresenter.saveUser(mUser);
    }

    @Override
    public void getUserDetail(User user) {
        mUser = user;
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String password = user.getPassWord();
        String email = user.getEmailAddress();

        mFirstName.setText(firstName);
        mLastName.setText(lastName);
        mPassword.setText(password);
        mEmailAddress.setText(email);
    }

    @Override
    public void saveUserFailure(String errorMessage) {

    }
}
