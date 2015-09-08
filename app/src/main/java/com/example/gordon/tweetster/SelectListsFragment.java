package com.example.gordon.tweetster;

import android.app.Activity;
import android.app.ListFragment;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;


public class SelectListsFragment extends ListFragment {

    private ArrayList<String> listNames = new ArrayList<>();
    private OnFragmentInteractionListener mListener;


    public SelectListsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // populate listNames
        getLists();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Call Twitter API to retrieve user's lists.
     */
    private void getLists() {
        TwitterSession session = Twitter.getSessionManager().getActiveSession();

        MyTwitterApiClient myTwitterApiClient = new MyTwitterApiClient(session);
        ListService listService = myTwitterApiClient.getListService();

        long userId = session.getUserId();
        String userName = session.getUserName();
        listService.getLists(userId, userName, false, new retrofit.Callback<List<TwitterList>>() {
            @Override
            public void success(List<TwitterList> twitterLists, Response response) {
                for (TwitterList twitterList : twitterLists) {
                    listNames.add(twitterList.getName());
                }
                setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listNames));
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
