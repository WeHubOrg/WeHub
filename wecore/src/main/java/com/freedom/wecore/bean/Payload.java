package com.freedom.wecore.bean;

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
}
