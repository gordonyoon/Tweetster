package com.example.gordon.tweetster;

import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterApiClient;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Extended Twitter API - retrieve user's lists
 */
interface ListService {
    @GET("/1.1/lists/list.json")
    void getLists(@Query("user_id") Long userId, @Query("screen_name") String screenName,
                  @Query("reverse") Boolean reverse, Callback<List<TwitterList>> cb);
}

public class MyTwitterApiClient extends TwitterApiClient {

    public MyTwitterApiClient(Session session) {
        super(session);
    }

    /**
     * Provide CustomService with defined endpoints
     */
    public ListService getListService() {
        return getService(ListService.class);
    }
}

