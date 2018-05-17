package com.freedom.wecore.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author vurtne on 17-May-18.
 */
@SuppressWarnings({"unused"})
public class Payload {
    /**
     * action : started
     *  "ref": null,
     "ref_type": "repository",
     "master_branch": "master",
     "description": null,
     "pusher_type": "user"
     */

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
}
