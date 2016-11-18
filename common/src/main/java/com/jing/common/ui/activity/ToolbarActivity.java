package com.jing.common.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.jing.common.R;


/**
 * Created by linux-sever-build5 on 10/10/16.
 */
public abstract class ToolbarActivity extends BaseActivity{


    protected AppBarLayout mAppBarLayout;
    protected Toolbar mToolbar;
    protected boolean mIsHidden = false;

    public void onToolbarClick(){

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if(mToolbar == null || mAppBarLayout == null)
            throw  new IllegalStateException("The subclass of ToolbarActivity must contain a toolbar");
        setSupportActionBar(mToolbar);
        mToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onToolbarClick();
            }
        });
        if(canBack()){
            ActionBar actionBar = getSupportActionBar();
            if(actionBar != null)
                actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= 21)
            mAppBarLayout.setElevation(10.6f);
    }

    public boolean canBack(){
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setmAppBarLayout(float alpha){
        mAppBarLayout.setAlpha(alpha);
    }

    protected void hideOrShowToolbar(){
        mAppBarLayout.animate().translationY(mIsHidden ? 0 : -mAppBarLayout.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        mIsHidden = !mIsHidden;
    }
}
