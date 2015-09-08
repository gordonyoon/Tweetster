package com.example.gordon.tweetster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.crashlytics.android.Crashlytics;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;


public class InitialActivity extends ActionBarActivity {

    // TODO: Your consumer key and secret should be obfuscated in your source code before shipping.
    private final String TWITTER_KEY = getString(R.string.twitter_key);
    private final String TWITTER_SECRET = getString(R.string.twitter_secret);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig), new Crashlytics());
        setContentView(R.layout.activity_initial);

        final Session activeSession = Twitter.getSessionManager().getActiveSession();
        if (activeSession != null) {
            startMainActivity();
        } else {
            startLoginActivity();
        }
        finish();
    }

    private void startMainActivity() {
        final Intent mainActivityIntent = new Intent(InitialActivity.this, MainActivity.class);
        startActivity(mainActivityIntent);
    }

    private void startLoginActivity() {
        final Intent loginActivityIntent = new Intent(InitialActivity.this, LoginActivity.class);
        startActivity(loginActivityIntent);
    }
}
