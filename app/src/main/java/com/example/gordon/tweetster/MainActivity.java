package com.example.gordon.tweetster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.twitter.sdk.android.Twitter;


public class MainActivity extends ActionBarActivity implements TweetListFragment.OnTweetSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.tweets_container) != null) {
            // avoid overlapping fragments
            if (savedInstanceState != null) {
                return;
            }

            TweetListFragment tlFragment = new TweetListFragment();
            getFragmentManager().beginTransaction().add(R.id.tweets_container, tlFragment).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_logout) {
            logout();
        } else if (id == R.id.action_get_lists) {
            startActivity(new Intent(this, SelectListsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        Twitter.getSessionManager().clearActiveSession();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onTweetSelected(String id) {

    }
}
