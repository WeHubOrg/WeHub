package com.freedom.wecore.bean;


import com.google.gson.annotations.SerializedName;

/**
 * @author vurtne on 5-May-18.
 */

public class Events {
    /**
     * id : 7674052789
     * type : WatchEvent
     * Actor : {"id":6065046,"login":"zhourihu5","display_login":"zhourihu5","gravatar_id":"","url":"https://api.github.com/users/zhourihu5","avatar_url":"https://avatars.githubusercontent.com/u/6065046?"}
     * repo : {"id":131495635,"name":"JiLiren/WeHub","url":"https://api.github.com/repos/JiLiren/WeHub"}
     * payload : {"action":"started"}
     * public : true
     * created_at : 2018-05-15T01:34:57Z
     * org : {"id":22327943,"login":"objectbox","gravatar_id":"","url":"https://api.github.com/orgs/objectbox","avatar_url":"https://avatars.githubusercontent.com/u/22327943?"}
     */

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

}
