package com.freedom.wecore.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author vurtne on 17-May-18.
 */
@SuppressWarnings({"unused"})
public class Commits implements Parcelable {

    private String sha;
    private Author author;
    private String message;
    private boolean distinct;
    private String url;

    protected Commits(Parcel in) {
        sha = in.readString();
        author = in.readParcelable(Author.class.getClassLoader());
        message = in.readString();
        distinct = in.readByte() != 0;
        url = in.readString();
    }

    public static final Creator<Commits> CREATOR = new Creator<Commits>() {
        @Override
        public Commits createFromParcel(Parcel in) {
            return new Commits(in);
        }

        @Override
        public Commits[] newArray(int size) {
            return new Commits[size];
        }
    };

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
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
        dest.writeString(sha);
        dest.writeParcelable(author, flags);
        dest.writeString(message);
        dest.writeByte((byte) (distinct ? 1 : 0));
        dest.writeString(url);
    }
}
