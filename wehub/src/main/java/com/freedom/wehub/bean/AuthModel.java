package com.freedom.wehub.bean;

import com.freedom.wecore.net.NetConfig;

import java.util.Arrays;
import java.util.List;

/**
 * @author vurtne on 1-May-18.
 */

public class AuthModel {

    public static class AuthRequest{
        private List<String> scopes;
        private String note;
        private String noteUrl;
        private String client_id;
        private String client_secret;

        public AuthRequest(){
            this.scopes = Arrays.asList("user", "repo", "gist", "notifications");
            this.note = NetConfig.APPLICATION_ID;
            this.client_id = NetConfig.CLIENT_ID;
            this.client_secret = NetConfig.CLIENT_SECRET;
            this.noteUrl = NetConfig.REDIRECT_URL;
        }
    }
}
