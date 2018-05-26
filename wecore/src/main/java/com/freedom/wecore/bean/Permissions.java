package com.freedom.wecore.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author  vurtne on 25-May-18.
 */
@SuppressWarnings({"unused"})
public class Permissions implements Parcelable {

    /**
     * admin : true
     * push : true
     * pull : true
     */

    private boolean admin;
    private boolean push;
    private boolean pull;

    protected Permissions(Parcel in) {
        admin = in.readByte() != 0;
        push = in.readByte() != 0;
        pull = in.readByte() != 0;
    }

    public static final Creator<Permissions> CREATOR = new Creator<Permissions>() {
        @Override
        public Permissions createFromParcel(Parcel in) {
            return new Permissions(in);
        }

        @Override
        public Permissions[] newArray(int size) {
            return new Permissions[size];
        }
    };

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isPush() {
        return push;
    }

    public void setPush(boolean push) {
        this.push = push;
    }

    public boolean isPull() {
        return pull;
    }

    public void setPull(boolean pull) {
        this.pull = pull;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (admin ? 1 : 0));
        dest.writeByte((byte) (push ? 1 : 0));
        dest.writeByte((byte) (pull ? 1 : 0));
    }
}
