package com.pixelsg.animanga.data.remote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pixelsg.animanga.data.models.Config;
import com.pixelsg.animanga.data.remote.animangaapp.AniMangaApi;
import com.pixelsg.animanga.data.remote.shikimori.ShikimoriApi;

public class RemoteDataSource {

    private final AniMangaApi aniMangaApi;
    private final ShikimoriApi shikimoriApi;

    public RemoteDataSource(Config config) {
        aniMangaApi = new AniMangaApi();
        shikimoriApi = new ShikimoriApi(config);
    }

    public LiveData<Event<Config>> getRemoteConfig() {
        MutableLiveData<Event<Config>> config = new MutableLiveData<>();
        config.postValue(Event.loading());

        aniMangaApi.getRemoteConfig(new OnLoadCompleteListener<Config>() {
            @Override
            public void onLoadComplete(Config data) {
                config.postValue(Event.success(data));
            }

            @Override
            public void onError(Event.Error error) {
                config.postValue(Event.error(error));
            }
        });

        return config;
    }
}
