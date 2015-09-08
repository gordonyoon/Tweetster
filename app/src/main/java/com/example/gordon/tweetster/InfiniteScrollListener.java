package com.example.gordon.tweetster;

import android.widget.AbsListView;

public abstract class InfiniteScrollListener implements AbsListView.OnScrollListener {
    private int bufferItemCount = TweetListFragment.LOAD_BUFFER_THRESHOLD;
    private int currentPage = 0;
    private int itemCount = 0;
    private boolean isLoading = true;

    public InfiniteScrollListener() {
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // Do Nothing
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // items were removed
        if (totalItemCount < itemCount) {
            this.itemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.isLoading = true;
            }
        }

        // loading new items completed
        if (isLoading && (totalItemCount > itemCount)) {
            isLoading = false;
            itemCount = totalItemCount;
            currentPage++;
        }

        // start loading new items when the threshold item is visible
        if (!isLoading && (totalItemCount - bufferItemCount) <= (firstVisibleItem + visibleItemCount)) {
            loadMore(currentPage + 1, totalItemCount);
            isLoading = true;
        }
    }

    public abstract void loadMore(int page, int totalItemsCount);
}
