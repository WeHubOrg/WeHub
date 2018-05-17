package com.freedom.wecore.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author vurtne on 17-May-18.
 */
@SuppressWarnings({"unused"})
public class Author implements Parcelable {

    private String email;
    private String name;

    protected Author(Parcel in) {
        email = in.readString();
        name = in.readString();
    }

    public static final Creator<Author> CREATOR = new Creator<Author>() {
        @Override
        public Author createFromParcel(Parcel in) {
            return new Author(in);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(name);
    }
}
