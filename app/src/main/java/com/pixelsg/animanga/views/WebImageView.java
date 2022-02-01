package com.pixelsg.animanga.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.pixelsg.animanga.data.models.Image;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class WebImageView extends AppCompatImageView {
    public WebImageView(@NonNull Context context) {
        super(context);
    }

    public WebImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WebImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Drawable newDrawable;

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
        } catch (RuntimeException e) {
            if(newDrawable == null)
                startResizingThread();
            else {
                setImageDrawable(newDrawable);
                newDrawable = null;
            }
        }
    }

    private void startResizingThread() {
        new Thread(() -> {
            newDrawable = getResizedDrawable(95);
            invalidate();
        }).start();
    }

    private Drawable getResizedDrawable(int p){
        if(getDrawable() == null)
            return null;
        Bitmap b = ((BitmapDrawable) getDrawable()).getBitmap();
        return new BitmapDrawable(getResources(), resizeBitmap(b, p));
    }

    private Bitmap resizeBitmap(Bitmap b, int p) {
        int newHeight = (int) (b.getHeight() * p / 100f);
        int newWidth = (int) (b.getWidth() * p / 100f);
        return Bitmap.createScaledBitmap(b, newWidth, newHeight, true);
    }

    private int blur = 0;

    public void setBlur(int blur) {
        this.blur = blur;
    }

    public void loadImage(Image image) {
        if(blur != 0)
            Glide
                    .with(this)
                    .load(image.original)
                    .centerCrop()
                    .thumbnail(
                            Glide
                                    .with(this)
                                    .load(image.preview)
                                    .transform(new MultiTransformation<>(
                                            new CenterCrop(),
                                            new BlurTransformation(blur)
                                    ))
                    )
                    .transform(new MultiTransformation<>(
                            new CenterCrop(),
                            new BlurTransformation(blur)
                    ))
                    .into(this);
        else
            Glide
                    .with(this)
                    .load(image.original)
                    .centerCrop()
                    .thumbnail(
                            Glide
                                    .with(this)
                                    .load(image.preview)
                                    .transform(new MultiTransformation<>(
                                            new CenterCrop(),
                                            new BlurTransformation(10)
                                    ))
                    )
                    .centerCrop()
                    .into(this);
    }

    @BindingAdapter("app:image")
    public static void setImage(WebImageView view, Image image) {
        view.loadImage(image);
    }

    @BindingAdapter("app:blur")
    public static void setBlur(WebImageView view, int blur) {
        view.setBlur(blur);
    }
}
