package com.pixelsg.animanga.data.remote;

public class Event<T> {

    public Status status;
    public T data;
    public Error error;

    public Event(Status status, T data, Error error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static Event loading() {
        return new Event(Status.LOADING, null, null);
    }

    public static Event error(Error error) {
        return new Event(Status.ERROR, null, error);
    }

    public static <T> Event<T> success(T data) {
        return new Event<>(Status.SUCCESS, data, null);
    }

    public enum Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    public enum Error {
        NETWORK,
        UNDEFINED,
        EMPTY,
        ACCESS_DENIED,
        NOT_FOUND,
        SERVER
    }

}
