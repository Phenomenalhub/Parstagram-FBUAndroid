package com.example.parstagram;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("gcAo3ZdhG8ZnYtSdVhR4cXbDI0KXo3Bx6mfW6Jzm")
                .clientKey("zj1lZZjekr0BdXiloTvF4KFUxtlSPqk1hgBB3zPA")
                .server("https://parseapi.back4app.com").build());
    }
}
