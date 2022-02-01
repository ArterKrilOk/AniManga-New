package com.pixelsg.animanga.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import com.pixelsg.animanga.data.models.ListManga;


public class PosterView extends WebImageView {
    public PosterView(@NonNull Context context) {
        super(context);
    }

    public PosterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PosterView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setManga(ListManga manga) {
        setBlur(manga.kind.equals("doujin")? 15 : 0);
        loadImage(manga.image);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(getMeasuredWidth(), (int)(getMeasuredWidth() * 1.55f));
    }

    @BindingAdapter("app:manga")
    public static void setManga(PosterView posterView, ListManga manga) {
        posterView.setManga(manga);
    }

}
