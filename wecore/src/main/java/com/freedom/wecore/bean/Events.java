package com.freedom.wecore.bean;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author vurtne on 5-May-18.
 */
@SuppressWarnings({"unused"})
public class Events implements Parcelable {

    private String id;
    private String type;
    private Actor actor;
    private Repo repo;
    private Payload payload;
    @SerializedName("public")
    private boolean publicX;
    @SerializedName("created_at")
    private String createdAt;
    private Org org;

    protected Events(Parcel in) {
        id = in.readString();
        type = in.readString();
        actor = in.readParcelable(Actor.class.getClassLoader());
        publicX = in.readByte() != 0;
        createdAt = in.readString();
    }

    public static final Creator<Events> CREATOR = new Creator<Events>() {
        @Override
        public Events createFromParcel(Parcel in) {
            return new Events(in);
        }

        @Override
        public Events[] newArray(int size) {
            return new Events[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public boolean isPublicX() {
        return publicX;
    }

    public void setPublicX(boolean publicX) {
        this.publicX = publicX;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(type);
        dest.writeParcelable(actor, flags);
        dest.writeByte((byte) (publicX ? 1 : 0));
        dest.writeString(createdAt);
    }
}
