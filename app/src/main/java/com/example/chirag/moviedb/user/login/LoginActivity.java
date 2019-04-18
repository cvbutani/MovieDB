package com.example.chirag.moviedb.user.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chirag.moviedb.R;
import com.example.chirag.moviedb.data.model.User;
import com.example.chirag.moviedb.dbmovie.DBHomeActivity;
import com.example.chirag.moviedb.user.register.RegisterActivity;
import com.example.chirag.moviedb.util.AppExecutors;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

        private AutoCompleteTextView mEmailView;

        private EditText mPasswordView;

        private View mProgressView;
        private View mLoginFormView;

        private LoginPresenter mPresenter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                setTheme(R.style.AppTheme);
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_login);

                mPresenter = new LoginPresenter(this, true);
                mPresenter.attachView(this);

                Logger.addLogAdapter(new AndroidLogAdapter());
                findView();

                Button button = findViewById(R.id.email_sign_in_button);
                button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                attemptLogin();
                        }
                });

                Button registerButton = findViewById(R.id.email_register_button);
                registerButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                                startActivity(intent);
                        }
                });

                mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                                        attemptLogin();
                                        return true;
                                }
                                return false;
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

                for (int i = 0; i < user.size(); i++) {
                        if (emailAddress.equals(user.get(i).getEmailAddress())) {
                                if (password.equals(user.get(i).getPassWord())) {
                                        showProgress(true);
                                        Intent intent = new Intent(LoginActivity.this, DBHomeActivity.class);
                                        intent.putExtra("EXTRA_EMAIL", emailAddress);
                                        startActivity(intent);
                                } else {
                                        mPasswordView.setError(getString(R.string.error_incorrect_password));
                                }
                        } else {
                                Logger.i("Something went wrong");
                                mPasswordView.setError(getString(R.string.error_incorrect_information));
                                mEmailView.setError(getString(R.string.error_incorrect_information));
                        }
                }
        }

        @Override
        public void getSignInError(String errorMessage) {

        }

        /**
         * Attempts to sign in or register the account specified by the login form.
         * If there are form errors (invalid email, missing fields, etc.), the
         * errors are presented and no actual login attempt is made.
         */
        private void attemptLogin() {
                // Reset errors.
                mEmailView.setError(null);
                mPasswordView.setError(null);

                // Store values at the time of the login attempt.
                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();

                boolean cancel = false;
                View focusView = null;

                // Check for a valid password, if the user entered one.
                if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
                        mPasswordView.setError(getString(R.string.error_invalid_password));
                        focusView = mPasswordView;
                        cancel = true;
                }

                // Check for a valid email address.
                if (TextUtils.isEmpty(email)) {
                        mEmailView.setError(getString(R.string.error_field_required));
                        focusView = mEmailView;
                        cancel = true;
                } else if (!isEmailValid(email)) {
                        mEmailView.setError(getString(R.string.error_invalid_email));
                        focusView = mEmailView;
                        cancel = true;
                }

                if (cancel) {
                        // There was an error; don't attempt login and focus the first
                        // form field with an error.
                        focusView.requestFocus();
                } else {
                        // Show a progress spinner, and kick off a background task to
                        // perform the user login attempt.
                        mPresenter.getUserAccountDetail();
                }
        }

        private boolean isEmailValid(String email) {
                return email.contains("@");
        }

        private boolean isPasswordValid(String password) {
                return password.length() > 4;
        }

        /**
         * Shows the progress UI and hides the login form.
         */
        @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
        private void showProgress(final boolean show) {
                // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
                // for very easy animations. If available, use these APIs to fade-in
                // the progress spinner.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

                        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                        mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                                        show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                                }
                        });

                        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                        mProgressView.animate().setDuration(shortAnimTime).alpha(
                                        show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                                }
                        });
                } else {
                        // The ViewPropertyAnimator APIs are not available, so simply show
                        // and hide the relevant UI components.
                        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
        }
}
