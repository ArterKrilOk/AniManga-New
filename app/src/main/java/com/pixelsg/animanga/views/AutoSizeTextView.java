package com.pixelsg.animanga.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.appcompat.widget.AppCompatTextView;

public class AutoSizeTextView extends AppCompatTextView {
    @SuppressLint("RestrictedApi")
    public AutoSizeTextView(Context context) {
        super(context);
        setAutoSizeTextTypeUniformWithConfiguration(8, 16, 2, TypedValue.COMPLEX_UNIT_SP);
    }

    @SuppressLint("RestrictedApi")
    public AutoSizeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAutoSizeTextTypeUniformWithConfiguration(8, 16, 2, TypedValue.COMPLEX_UNIT_SP);
    }

    @SuppressLint("RestrictedApi")
    public AutoSizeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAutoSizeTextTypeUniformWithConfiguration(8, 16, 2, TypedValue.COMPLEX_UNIT_SP);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);

        //setHeight(getHeight(text.length()));
    }

    private int getHeight(int count){
        if(count > 35)
            return 160;
        if(count > 20)
            return 120;
        return 80;
    }
}