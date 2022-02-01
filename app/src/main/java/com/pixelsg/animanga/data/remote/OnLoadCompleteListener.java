package com.pixelsg.animanga.data.remote;

import android.util.Log;

public interface OnLoadCompleteListener<T> {
    void onLoadComplete(T data);
    default void onError(Event.Error error) {
        Log.println(Log.ERROR, "Remote", error.name());
    }
}
