package com.freedom.wecore.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author vurtne on 5-May-18.
 */
@SuppressWarnings({"unused"})
public class Org {
    /**
     * id : 22327943
     * login : objectbox
     * gravatar_id :
     * url : https://api.github.com/orgs/objectbox
     * avatar_url : https://avatars.githubusercontent.com/u/22327943?
     */

    private int id;
    private String login;
    @SerializedName("gravatar_id")
    private String gravatarId;
    private String url;
    @SerializedName("avatar_url")
    private String avatarUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
