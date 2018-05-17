package com.freedom.wecore.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author vurtne on 5-May-18.
 */
@SuppressWarnings({"unused"})
public class Repo implements Parcelable {

    /**
     * id : 131495635
     * name : JiLiren/WeHub
     * url : https://api.github.com/repos/JiLiren/WeHub
     */

    private int id;
    private String name;
    private String url;

    protected Repo(Parcel in) {
        id = in.readInt();
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<Repo> CREATOR = new Creator<Repo>() {
        @Override
        public Repo createFromParcel(Parcel in) {
            return new Repo(in);
        }

        @Override
        public Repo[] newArray(int size) {
            return new Repo[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(url);
    }
}
