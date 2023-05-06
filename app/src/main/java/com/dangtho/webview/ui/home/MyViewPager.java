package com.dangtho.webview.ui.home;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class MyViewPager extends ViewPager {
    private boolean isSiwpe = false;

    public void setSiwpe(boolean siwpe) {
        isSiwpe = siwpe;
    }

    public MyViewPager(@NonNull @NotNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isSiwpe) return false;
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isSiwpe) return false;
        return super.onInterceptTouchEvent(ev);
    }
}
