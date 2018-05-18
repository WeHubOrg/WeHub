package com.freedom.wecore.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 *  @author vurtne on 17-May-18.
 */
public class Issues implements Parcelable {
    private String url;
    private String repository_url;
    private String labels_url;
    private String comments_url;
    private String events_url;
    private String html_url;
    private int id;
    private int number;
    private String title;
    private User user;
    private String state;
    private boolean locked;
    private Object assignee;
    private Object milestone;
    private int comments;
    private String created_at;
    private String updated_at;
    private Object closed_at;
    private String author_association;
    private String body;
    private List<?> labels;
    private List<?> assignees;

    protected Issues(Parcel in) {
        url = in.readString();
        repository_url = in.readString();
        labels_url = in.readString();
        comments_url = in.readString();
        events_url = in.readString();
        html_url = in.readString();
        id = in.readInt();
        number = in.readInt();
        title = in.readString();
        user = in.readParcelable(User.class.getClassLoader());
        state = in.readString();
        locked = in.readByte() != 0;
        comments = in.readInt();
        created_at = in.readString();
        updated_at = in.readString();
        author_association = in.readString();
        body = in.readString();
    }

    public static final Creator<Issues> CREATOR = new Creator<Issues>() {
        @Override
        public Issues createFromParcel(Parcel in) {
            return new Issues(in);
        }

        @Override
        public Issues[] newArray(int size) {
            return new Issues[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRepository_url() {
        return repository_url;
    }

    public void setRepository_url(String repository_url) {
        this.repository_url = repository_url;
    }

    public String getLabels_url() {
        return labels_url;
    }

    public void setLabels_url(String labels_url) {
        this.labels_url = labels_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Object getAssignee() {
        return assignee;
    }

    public void setAssignee(Object assignee) {
        this.assignee = assignee;
    }

    public Object getMilestone() {
        return milestone;
    }

    public void setMilestone(Object milestone) {
        this.milestone = milestone;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Object getClosed_at() {
        return closed_at;
    }

    public void setClosed_at(Object closed_at) {
        this.closed_at = closed_at;
    }

    public String getAuthor_association() {
        return author_association;
    }

    public void setAuthor_association(String author_association) {
        this.author_association = author_association;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<?> getLabels() {
        return labels;
    }

    public void setLabels(List<?> labels) {
        this.labels = labels;
    }

    public List<?> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<?> assignees) {
        this.assignees = assignees;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(repository_url);
        dest.writeString(labels_url);
        dest.writeString(comments_url);
        dest.writeString(events_url);
        dest.writeString(html_url);
        dest.writeInt(id);
        dest.writeInt(number);
        dest.writeString(title);
        dest.writeParcelable(user, flags);
        dest.writeString(state);
        dest.writeByte((byte) (locked ? 1 : 0));
        dest.writeInt(comments);
        dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeString(author_association);
        dest.writeString(body);
    }
}
