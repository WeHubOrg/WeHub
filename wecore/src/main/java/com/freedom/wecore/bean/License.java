package com.freedom.wecore.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author vurtne on 25-May-18.
 */
@SuppressWarnings({"unused"})
public class License implements Parcelable {

    private String key;
    private String name;
    @SerializedName("spdx_id")
    private Object spdxId;
    private Object url;


    protected License(Parcel in) {
        key = in.readString();
        name = in.readString();
    }

    public static final Creator<License> CREATOR = new Creator<License>() {
        @Override
        public License createFromParcel(Parcel in) {
            return new License(in);
        }

        @Override
        public License[] newArray(int size) {
            return new License[size];
        }
    };

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getSpdxId() {
        return spdxId;
    }

    public void setSpdxId(Object spdxId) {
        this.spdxId = spdxId;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(name);
    }
}
