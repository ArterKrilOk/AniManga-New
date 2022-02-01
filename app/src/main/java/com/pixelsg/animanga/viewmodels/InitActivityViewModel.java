package com.pixelsg.animanga.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pixelsg.animanga.App;
import com.pixelsg.animanga.R;
import com.pixelsg.animanga.data.AppRepository;
import com.pixelsg.animanga.data.models.Config;
import com.pixelsg.animanga.data.remote.Event;

public class InitActivityViewModel extends AndroidViewModel {

    public final MutableLiveData<String> message = new MutableLiveData<>();
    public final MutableLiveData<Boolean> retry = new MutableLiveData<>(false);
    private final AppRepository appRepository;

    public InitActivityViewModel(@NonNull Application application) {
        super(application);

        appRepository = AppRepository.getInstance(null);
        startLoading();
    }

    public void retry() {
        startLoading();
    }

    private void startLoading() {
        message.postValue(getApplication().getString(R.string.loading));
    }

    public LiveData<Event<Config>> getConfig() {
        return appRepository.getRemoteConfig();
    }

    private void loading() {
        message.postValue(getApplication().getString(R.string.loading_config_data));
        retry.postValue(false);
    }

    public void configsEvent(Event<Config> configEvent) {
        switch (configEvent.status) {
            case LOADING: loading(); break;
            case ERROR: showError(configEvent.error); break;
            case SUCCESS: configsLoaded(configEvent.data); break;
        }
    }

    private void configsLoaded(Config config) {
        ((App) getA5pplication()).setConfig(config);
        appRepository.recreate(config);
        message.postValue("Loading Successful");
    }

    private void showError(Event.Error error) {
        message.postValue("Error: " + error.name());
        retry.postValue(true);
    }
}
