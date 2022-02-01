package com.pixelsg.animanga.data.remote.animangaapp;

import com.pixelsg.animanga.data.models.Config;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("config.cnf")
    Call<Config> getRemoteConfig();
}
