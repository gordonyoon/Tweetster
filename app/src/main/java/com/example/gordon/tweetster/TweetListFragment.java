package com.example.gordon.tweetster;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetViewFetchAdapter;

import java.util.List;

/**
 * Activities containing this fragment MUST implement the {@link OnTweetSelectedListener}
 * interface.
 */
public class TweetListFragment extends ListFragment {

    final private static int LOAD_COUNT = 20;
    final protected static int LOAD_BUFFER_THRESHOLD = 2;

    private TweetViewFetchAdapter mAdapter = null;

    private OnTweetSelectedListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TweetListFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnTweetSelectedListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTweetSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new TweetViewFetchAdapter<>(getActivity());
        setListAdapter(mAdapter);

        loadMoreTimelineTweets();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // scrollListener must be set in onActivityCreated()
        getListView().setOnScrollListener(new InfiniteScrollListener() {
            @Override
            public void loadMore(int page, int totalItemsCount) {
                loadMoreTimelineTweets();
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Add LOAD_COUNT Tweets to the current view.
     */
    private void loadMoreTimelineTweets() {
        Integer count = LOAD_COUNT;
        Long sinceId = null;
        Long maxId = getMaxId();
        Boolean trimUser = false;
        Boolean excludeReplies = false;
        Boolean contributorDetails = true;
        Boolean includeEntities = true;

        TwitterApiClient twitterApiClient = Twitter.getApiClient();
        twitterApiClient.getStatusesService().homeTimeline(count, sinceId, maxId,
                trimUser, excludeReplies, contributorDetails, includeEntities,
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> listResult) {
                        mAdapter.getTweets().addAll(listResult.data);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void failure(TwitterException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    private Long getMaxId() {
        List<Tweet> tweets = mAdapter.getTweets();
        if (tweets.size() > 0) {
            Tweet t = tweets.get(tweets.size() - 1);
            return t.id - 1;  // exclude oldest visible Tweet
        } else {
            return null;
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnTweetSelectedListener {
        // TODO: Update argument type and name
        public void onTweetSelected(String id);
    }
}