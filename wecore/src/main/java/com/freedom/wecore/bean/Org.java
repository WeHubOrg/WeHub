package com.freedom.wecore.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author vurtne on 5-May-18.
 */
@SuppressWarnings({"unused"})
public class Org implements Parcelable {
    /**
     * id : 22327943
     * login : objectbox
     * gravatar_id :
     * url : https://api.github.com/orgs/objectbox
     * avatar_url : https://avatars.githubusercontent.com/u/22327943?
     */

    private int id;
    private String login;
    @SerializedName("gravatar_id")
    private String gravatarId;
    private String url;
    @SerializedName("avatar_url")
    private String avatarUrl;

    protected Org(Parcel in) {
        id = in.readInt();
        login = in.readString();
        gravatarId = in.readString();
        url = in.readString();
        avatarUrl = in.readString();
    }

    public static final Creator<Org> CREATOR = new Creator<Org>() {
        @Override
        public Org createFromParcel(Parcel in) {
            return new Org(in);
        }

        @Override
        public Org[] newArray(int size) {
            return new Org[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
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
        dest.writeInt(id);
        dest.writeString(login);
        dest.writeString(gravatarId);
        dest.writeString(url);
        dest.writeString(avatarUrl);
    }
}
