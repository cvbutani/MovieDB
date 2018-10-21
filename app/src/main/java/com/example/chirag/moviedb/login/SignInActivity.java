package com.example.chirag.moviedb.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.chirag.moviedb.R;
import com.example.chirag.moviedb.data.model.User;
import com.example.chirag.moviedb.dbmovie.DBHomeActivity;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.List;

public class SignInActivity extends AppCompatActivity implements SignInContract.View {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    private SignInPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mPresenter = new SignInPresenter(this, true);
        mPresenter.attachView(this);

        Logger.addLogAdapter(new AndroidLogAdapter());
        findView();
        createNewUser();

        Button button = findViewById(R.id.email_sign_in_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getUserAccountDetail();
            }
        });
    }

    private void findView() {
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mProgressView = findViewById(R.id.login_progress);
        mLoginFormView = findViewById(R.id.login_form);
    }

    @Override
    public void getUserDetail(List<User> user) {
        String emailAddress = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        for(int i =0; i<user.size(); i++) {
            if (emailAddress.equals(user.get(i).getEmailAddress()) && password.equals(user.get(i).getPassWord())) {
                Intent intent = new Intent(this, DBHomeActivity.class);
                startActivity(intent);
            } else {
                Logger.i("Something went wrong");
            }
        }
    }

    @Override
    public void getSignInError(String errorMessage) {

    }

    @Override
    public void createNewUser() {
        User user = new User("12qwaszx", "Chirag", "Butani", "chirag@ctdi.comm");
        mPresenter.createNewUserAccount(user);
    }
}
