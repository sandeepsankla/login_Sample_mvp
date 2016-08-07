/*
 *
 *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.sasa.mvpexample.app.Login.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sasa.mvpexample.app.Login.presenter.LoginPresenter;
import com.sasa.mvpexample.app.Login.presenter.LoginPresenterImpl;
import com.sasa.mvpexample.app.R;

public class LoginActivity extends Activity implements LoginView, View.OnClickListener {

    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       initViews();
        initClickListener();


        presenter = new LoginPresenterImpl(this);
    }

    private void initClickListener() {
        findViewById(R.id.button).setOnClickListener(this);
    }

    private void initViews() {
        progressBar = (ProgressBar) findViewById(R.id.progress);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
    }

    @Override protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override public void setUsernameError() {
        username.setError(getString(R.string.username_error));
    }

    @Override public void setPasswordError() {
        password.setError(getString(R.string.password_error));
    }

    @Override public void ShowSuccessResult() {
        Toast.makeText(LoginActivity.this, "Welcome to Login Page!!", Toast.LENGTH_SHORT).show();
    }

    @Override public void showFailureResult() {
        Toast.makeText(LoginActivity.this, "Server Error, Please try again.", Toast.LENGTH_SHORT).show();
    }

    @Override public void onClick(View v) {
        presenter.validateCredentials(username.getText().toString(), password.getText().toString());
    }
}
