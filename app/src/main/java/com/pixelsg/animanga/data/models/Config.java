package com.pixelsg.animanga.data.models;

import com.google.gson.annotations.SerializedName;

public class Config {
    @SerializedName("config_version")
    public int configVersion;

    @SerializedName("remote_version")
    public int remoteVersion;

    @SerializedName("remote_version_code")
    public String remoteVersionCode;

    @SerializedName("update_descr")
    public String updateDescr;

    @SerializedName("update_date")
    public String updateDate;

    @SerializedName("shikimori_url")
    public String shikimoriUrl;
}
