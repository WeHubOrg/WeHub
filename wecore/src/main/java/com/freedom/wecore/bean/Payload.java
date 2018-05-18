package com.freedom.wecore.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author vurtne on 17-May-18.
 */
@SuppressWarnings({"unused"})
public class Payload implements Parcelable {

    private String action;
    private String description;
    @SerializedName("push_id")
    private long pushId;
    private int size;
    @SerializedName("distinct_size")
    private int distinctSize;
    private String ref;
    private String head;
    private String before;
    private List<Commits> commits;
    private Issues issue;
    private Comment comment;

    protected Payload(Parcel in) {
        action = in.readString();
        description = in.readString();
        pushId = in.readLong();
        size = in.readInt();
        distinctSize = in.readInt();
        ref = in.readString();
        head = in.readString();
        before = in.readString();
        commits = in.createTypedArrayList(Commits.CREATOR);
        issue = in.readParcelable(Issues.class.getClassLoader());
        comment = in.readParcelable(Comment.class.getClassLoader());
    }

    public static final Creator<Payload> CREATOR = new Creator<Payload>() {
        @Override
        public Payload createFromParcel(Parcel in) {
            return new Payload(in);
        }

        @Override
        public Payload[] newArray(int size) {
            return new Payload[size];
        }
    };

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPushId() {
        return pushId;
    }

    public void setPushId(long pushId) {
        this.pushId = pushId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getDistinctSize() {
        return distinctSize;
    }

    public void setDistinctSize(int distinctSize) {
        this.distinctSize = distinctSize;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public List<Commits> getCommits() {
        return commits;
    }

    public void setCommits(List<Commits> commits) {
        this.commits = commits;
    }

    public Issues getIssue() {
        return issue;
    }

    public void setIssue(Issues issue) {
        this.issue = issue;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public static Creator<Payload> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(action);
        dest.writeString(description);
        dest.writeLong(pushId);
        dest.writeInt(size);
        dest.writeInt(distinctSize);
        dest.writeString(ref);
        dest.writeString(head);
        dest.writeString(before);
        dest.writeTypedList(commits);
        if (issue != null){
            dest.writeParcelable(issue, flags);
        }else {
            dest.writeParcelable(null, flags);
        }
        if (issue != null){
            dest.writeParcelable(comment, flags);
        }else {
            dest.writeParcelable(null, flags);
        }

    }

}
