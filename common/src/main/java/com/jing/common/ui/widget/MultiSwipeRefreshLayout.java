package com.jing.common.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.jing.common.R;


/**
 * Created by linux-sever-build5 on 10/11/16.
 */
public class MultiSwipeRefreshLayout extends SwipeRefreshLayout{
    private CanChildScrollUpCallback mCanChildScrollUpCallback;
    private Drawable mForegroundDrawable;

    public MultiSwipeRefreshLayout(Context context){
        this(context, null);
    }

    public MultiSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MultiSwipeRefreshLayout);
        mForegroundDrawable = array.getDrawable(R.styleable.MultiSwipeRefreshLayout_foreground);
        if(mForegroundDrawable != null){
            mForegroundDrawable.setCallback(this);
            setWillNotDraw(false);
        }
        array.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(mForegroundDrawable != null)
            mForegroundDrawable.setBounds(0,0,w,h);
    }

    public interface CanChildScrollUpCallback {
        boolean canSwipeRefreshChildScrollUp();
    }

    @Override
    public boolean canChildScrollUp() {
        if (mCanChildScrollUpCallback != null)
            return mCanChildScrollUpCallback.canSwipeRefreshChildScrollUp();
        return super.canChildScrollUp();
    }

    public void setCanChildScrollUpCallback(CanChildScrollUpCallback mCanChildScrollUpCallback) {
        this.mCanChildScrollUpCallback = mCanChildScrollUpCallback;
    }

    public interface SwipeRefreshLayer {
        void requestDataRefresh();

        void setRefresh(boolean refresh);

        void setProgressViewOffset(boolean scale, int start, int end);

        void setCanChildScrollUpCallback(MultiSwipeRefreshLayout.CanChildScrollUpCallback callback);
    }
}
