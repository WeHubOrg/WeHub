package com.freedom.wecore.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author vurtne on 18-May-18.
 */
@SuppressWarnings({"unused"})
public class Comment implements Parcelable {
    private String url;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("issue_url")
    private String issueUrl;
    private int id;
    private User user;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("author_association")
    private String authorAssociation;
    private String body;

    protected Comment(Parcel in) {
        url = in.readString();
        htmlUrl = in.readString();
        issueUrl = in.readString();
        id = in.readInt();
        user = in.readParcelable(User.class.getClassLoader());
        createdAt = in.readString();
        updatedAt = in.readString();
        authorAssociation = in.readString();
        body = in.readString();
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getIssueUrl() {
        return issueUrl;
    }

    public void setIssueUrl(String issueUrl) {
        this.issueUrl = issueUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAuthorAssociation() {
        return authorAssociation;
    }

    public void setAuthorAssociation(String authorAssociation) {
        this.authorAssociation = authorAssociation;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(htmlUrl);
        dest.writeString(issueUrl);
        dest.writeInt(id);
        dest.writeParcelable(user, flags);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(authorAssociation);
        dest.writeString(body);
    }
}
