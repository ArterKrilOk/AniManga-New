package com.pixelsg.animanga.data;


import androidx.lifecycle.LiveData;

import com.pixelsg.animanga.App;
import com.pixelsg.animanga.data.models.Config;
import com.pixelsg.animanga.data.remote.Event;
import com.pixelsg.animanga.data.remote.RemoteDataSource;

public class AppRepository {

    private static AppRepository INSTANCE;
    private final RemoteDataSource remoteDataSource;

    private AppRepository(Config config) {
        remoteDataSource = new RemoteDataSource(config);
    }

    public static AppRepository getInstance(Config config) {
        if(INSTANCE == null)
            INSTANCE = new AppRepository(config);
        return INSTANCE;
    }

    public AppRepository recreate(Config config) {
        INSTANCE = null;
        return getInstance(config);
    }

    public LiveData<Event<Config>> getRemoteConfig() {
        return remoteDataSource.getRemoteConfig();
    }
}
