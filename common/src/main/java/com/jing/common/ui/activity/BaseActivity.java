package com.jing.common.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jing.common.ui.widget.StatusBarCompat;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preSetContentView();
        setContentView(getLayoutId());
        initVariabkes();
        initViews(savedInstanceState);
        loadData();
    }

    public void preSetContentView(){

    }

    protected abstract void initVariabkes();
    protected abstract void initViews(Bundle savedInstanceState);
    protected abstract void loadData();
    protected abstract int getLayoutId();

    protected void SetTranslanteBar(){
        StatusBarCompat.translucentStatusBar(this);
    }

}
