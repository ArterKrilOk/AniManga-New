package com.pixelsg.animanga.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;

import com.pixelsg.animanga.R;

public class AnimatedTextView extends AppCompatTextView {
    public AnimatedTextView(@NonNull Context context) {
        super(context);
        init(null);
    }

    public AnimatedTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public AnimatedTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private int delay = 25;
    private Handler handler;

    private String curText, text;
    private int i;

    private void init(AttributeSet attrs) {
        handler = new Handler();

        if(attrs == null)
            return;

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.AnimatedTextView, 0, 0);
        try {
            delay = ta.getInteger(R.styleable.AnimatedTextView_animationDelay, delay);
        } finally {
            ta.recycle();
        }
    }

    public void setText(int resId, boolean animated){
        setText(getResources().getText(resId).toString(), animated);
    }

    public void setText(String text, boolean animated) {
        this.text = text;

        if(!animated) {
            setText(text);
            return;
        }

        startAnimation();
    }

    private void startAnimation() {
        handler.removeCallbacksAndMessages(null);
        if(text == null)
            return;
        if(text.isEmpty())
            return;

        if(text.length() % 2 == 0)
            text += " ";
        i = 1;
        curText = "" + text.toCharArray()[text.length() / 2];

        doAnimation();
    }

    private void doAnimation() {
        handler.postDelayed(()->{
            curText = text.toCharArray()[text.length() / 2 - i] +
                    curText +
                    text.toCharArray()[text.length() / 2 + i];
            setText("「" + curText + "」");
            i++;

            if(i > text.length() / 2)
                setText(getFinalText(curText));
            else
                doAnimation();
        }, delay);
    }

    private String getFinalText(String text) {
        if(text.endsWith(" "))
            text = " " + text;
        else
            text = " " + text + " ";
        return "「" + text + "」";
    }

    @BindingAdapter("app:animatedText")
    public static void setAnimatedText(AnimatedTextView view, String text) {
        view.setText(text, true);
    }
}
