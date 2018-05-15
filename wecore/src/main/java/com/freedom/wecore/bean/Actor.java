package com.freedom.wecore.bean;

import com.google.gson.annotations.SerializedName;

public class Actor {
    /**
     * id : 6065046
     * login : zhourihu5
     * display_login : zhourihu5
     * gravatar_id :
     * url : https://api.github.com/users/zhourihu5
     * avatar_url : https://avatars.githubusercontent.com/u/6065046?
     */

    private int id;
    private String login;
    @SerializedName("display_login")
    private String displayLogin;
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

    public String getDisplayLogin() {
        return displayLogin;
    }

    public void setDisplayLogin(String displayLogin) {
        this.displayLogin = displayLogin;
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
