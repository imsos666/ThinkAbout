package com.jing.common.ui.activity;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;

import com.jing.common.R;
import com.jing.common.ui.widget.MultiSwipeRefreshLayout;

public abstract class SwipeRefreshBaseActivity extends ToolbarActivity implements MultiSwipeRefreshLayout.SwipeRefreshLayer {

    public MultiSwipeRefreshLayout mSwipeRefreshLayout;
    private boolean mIsRequestDataRefresh = false;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        mSwipeRefreshLayout = (MultiSwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        trySetupSwipeRefresh();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    void trySetupSwipeRefresh(){
        if(mSwipeRefreshLayout != null){
            mSwipeRefreshLayout.setColorSchemeColors(
                    R.color.refresh_progress_3, R.color.refresh_progress_2,R.color.refresh_progress_1
            );
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    requestDataRefresh();
                }
            });
        }
    }

    @Override
    public void setRefresh(boolean refresh) {
        if (mSwipeRefreshLayout == null)
            return;

        if(refresh){
           mSwipeRefreshLayout.setRefreshing(true);
        }else{
            mSwipeRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(mSwipeRefreshLayout != null)
                        mSwipeRefreshLayout.setRefreshing(false);
                }
            }, 600);
        }
    }

    @Override
    public void setProgressViewOffset(boolean scale, int start, int end) {
        mSwipeRefreshLayout.setProgressViewOffset(scale,start,end);
    }

    @Override
    public void setCanChildScrollUpCallback(MultiSwipeRefreshLayout.CanChildScrollUpCallback callback) {
        mSwipeRefreshLayout.setCanChildScrollUpCallback(callback);
    }
}
