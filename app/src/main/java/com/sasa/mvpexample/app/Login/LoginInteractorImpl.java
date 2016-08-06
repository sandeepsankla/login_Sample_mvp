package com.sasa.mvpexample.app.Login;

import android.os.Handler;

public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        // Mock login. I'm creating a handler to delay the answer a couple of seconds
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                    listener.onSuccess();
                // listener.onError().; depends on api result or processing.
            }
        }, 2000);
    }
}
