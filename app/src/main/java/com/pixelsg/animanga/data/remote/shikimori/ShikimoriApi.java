package com.pixelsg.animanga.data.remote.shikimori;

import com.pixelsg.animanga.data.models.Config;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShikimoriApi {
    private Api api;

    public static final int TIMEOUT = 100000;

    public ShikimoriApi(Config config) {
        if(config == null)
            return;

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
                .baseUrl(config.shikimoriUrl)
                .client(httpClient.build())
                .build();
        api = retrofit.create(Api.class);
    }
}
