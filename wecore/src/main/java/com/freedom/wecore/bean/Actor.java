package com.freedom.wecore.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author vurtne on 5-May-18.
 */
@SuppressWarnings({"unused"})
public class Actor implements Parcelable {
    /**
     * id : 6065046
     * login : zhourihu5
     * display_login : zhourihu5
     * gravatar_id :
     * url : https://api.github.com/users/zhourihu5
     * avatar_url : https://avatars.githubusercontent.com/u/6065046?
     */

    private long id;
    private String login;
    @SerializedName("display_login")
    private String displayLogin;
    private String url;
    @SerializedName("gravatar_id")
    private String gravatarId;
    @SerializedName("avatar_url")
    private String avatarUrl;

    protected Actor(Parcel parcel) {
        this.id = parcel.readLong();
        this.login = parcel.readString();
        this.displayLogin = parcel.readString();
        this.avatarUrl = parcel.readString();
        this.url = parcel.readString();
        this.gravatarId = parcel.readString();
        this.avatarUrl = parcel.readString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDisplayLogin() {
        return displayLogin;
    }

    public void setDisplayLogin(String displayLogin) {
        this.displayLogin = displayLogin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.login);
        dest.writeString(this.displayLogin);
        dest.writeString(this.url);
        dest.writeString(this.gravatarId);
        dest.writeString(this.avatarUrl);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
