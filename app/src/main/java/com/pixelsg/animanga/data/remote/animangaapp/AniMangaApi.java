package com.pixelsg.animanga.data.remote.animangaapp;

import com.pixelsg.animanga.data.models.Config;
import com.pixelsg.animanga.data.remote.Event.Error;
import com.pixelsg.animanga.data.remote.OnLoadCompleteListener;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AniMangaApi {
    private final Api api;
    public static final String BASE_URL = "https://api.animanga-app.ru/napp/";
    public static final int TIMEOUT = 100000;

    public AniMangaApi() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS);
        httpClient.readTimeout(TIMEOUT, TimeUnit.MILLISECONDS);

        httpClient.addInterceptor(chain -> {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Region", "ru")
                    .build();
            return chain.proceed(request);
        });

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .build();
        api = retrofit.create(Api.class);
    }

    public void getRemoteConfig(OnLoadCompleteListener<Config> onLoadCompleteListener) {
        api.getRemoteConfig().enqueue(new Callback<Config>() {
            @Override
            public void onResponse(Call<Config> call, Response<Config> response) {
                if(response.isSuccessful()) {
                    if(response.body() == null)
                        onLoadCompleteListener.onError(Error.EMPTY);
                    else
                        onLoadCompleteListener.onLoadComplete(response.body());
                } else
                    onLoadCompleteListener.onError(fromCode(response.code()));
            }

            @Override
            public void onFailure(Call<Config> call, Throwable t) {
                onLoadCompleteListener.onError(Error.NETWORK);
            }
        });
    }

    private Error fromCode(int code) {
        switch (code) {
            case 404: return Error.NOT_FOUND;
            case 503: return Error.SERVER;
            case 403: return Error.ACCESS_DENIED;
        }
        return Error.UNDEFINED;
    }
}
