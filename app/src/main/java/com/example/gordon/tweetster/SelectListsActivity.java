package com.example.gordon.tweetster;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class SelectListsActivity extends ActionBarActivity implements SelectListsFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_lists);

        if (findViewById(R.id.lists_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
