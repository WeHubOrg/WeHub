package com.freedom.wehub.quest;

import com.freedom.wecore.bean.BaseToken;
import com.freedom.wecore.bean.Repository;
import com.freedom.wecore.bean.User;
import com.freedom.wecore.net.OnResponseListener;
import com.freedom.wecore.net.RetrofitClient;
import com.freedom.wehub.model.AuthModel;
import com.freedom.wehub.model.RepositoryModel;

import java.util.List;

/**
 * @author vurtne on 1-May-18.
 */

public class AuthService extends RetrofitClient {

    /**
     * 获取token
     */
    public void requestToken(String token, AuthModel.AuthRequest authRequest, OnResponseListener<BaseToken> listener) {
        createRequest(listener)
                .clazz(BaseToken.class)
                .request(RetrofitClient.getTokenService(token).post("authorizations", authRequest));
    }

    public void requestPersonInfo(OnResponseListener<User> listener) {
        createRequest(listener)
                .clazz(User.class)
                .request(RetrofitClient.getService().getPersonInfo(true));
    }

    public void requestPersonInfo(String user, OnResponseListener<User> listener) {
        createRequest(listener)
                .clazz(User.class)
                .request(RetrofitClient.getService().getPersonInfo(true, user));
    }

    public void requestRepositories(int page,String type,String sort,String direction, OnResponseListener<RepositoryModel> listener) {
        createRequest(listener)
                .clazz(RepositoryModel.class)
                .request(RetrofitClient.getService().getRepositories(true, page,type,sort,direction));
    }
}