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

package com.sasa.mvpexample.app.Login;

import android.text.TextUtils;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
    }

    @Override public void validateCredentials(String username, String password) {
        if (loginView != null) {
            if(!validateUserName(username))
                loginView.setUsernameError();
            else  if(!validateUserEmail(password))
            loginView.setPasswordError();
            else {
                loginView.showProgress();
                loginInteractor.login(username, password, this);
            }

        }


    }

    private boolean validateUserEmail(String password) {
        boolean  isUserEmailValid = false;
        if(!TextUtils.isEmpty(password) )
            isUserEmailValid = true;
        return isUserEmailValid;
    }

    private boolean validateUserName(String username) {
        boolean  isUserNameValid = false;
        if(!TextUtils.isEmpty(username) )
            isUserNameValid = true;
        return isUserNameValid;

    }

    @Override public void onDestroy() {
        loginView = null;
    }

    @Override public void onFailure() {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.showFailureResult();
        }
    }

    @Override public void onSuccess() {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.ShowSuccessResult();
        }
    }
}
