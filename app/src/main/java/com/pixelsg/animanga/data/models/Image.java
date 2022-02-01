package com.pixelsg.animanga.data.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName("original")
    @Expose
    public String original;
    @SerializedName("preview")
    @Expose
    public String preview;
}