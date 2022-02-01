package com.pixelsg.animanga.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListManga {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("russian")
    @Expose
    public String russian;
    @SerializedName("image")
    @Expose
    public Image image;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("kind")
    @Expose
    public String kind;
    @SerializedName("score")
    @Expose
    public String score;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("aired_on")
    @Expose
    public String airedOn;
}