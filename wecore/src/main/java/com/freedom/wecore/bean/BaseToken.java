package com.freedom.wecore.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * @author vurtne on 1-May-18.
 */
@SuppressWarnings({"unused"})
public class BaseToken implements Parcelable {
    private int id;
    private String url;
    private String token;
    @SerializedName("created_at")
    private Date createdAt;
    @SerializedName("updated_at")
    private Date updatedAt;
    private List<String> scopes;


    protected BaseToken(Parcel in) {
        id = in.readInt();
        url = in.readString();
        token = in.readString();
        scopes = in.createStringArrayList();
    }

    public static final Creator<BaseToken> CREATOR = new Creator<BaseToken>() {
        @Override
        public BaseToken createFromParcel(Parcel in) {
            return new BaseToken(in);
        }

        @Override
        public BaseToken[] newArray(int size) {
            return new BaseToken[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(url);
        dest.writeString(token);
        dest.writeStringList(scopes);
    }
}
