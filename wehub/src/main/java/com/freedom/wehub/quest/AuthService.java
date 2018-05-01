package com.freedom.wehub.quest;

import com.freedom.wecore.common.BaseToken;
import com.freedom.wecore.common.User;
import com.freedom.wecore.net.OnResponseListener;
import com.freedom.wecore.net.RetrofitClient;
import com.freedom.wehub.bean.AuthModel;

/**
 * @author vurtne on 1-May-18.
 */

public class AuthService extends RetrofitClient {

    /**
     * 获取token
     * */
    public void requestToken(String token,AuthModel.AuthRequest authRequest,OnResponseListener<BaseToken> listener){
        createRequest(listener)
                .clazz(BaseToken.class)
                .request(RetrofitClient.getTokenService(token).post("authorizations",authRequest));
    }

    public void requestPersonInfo(OnResponseListener<User> listener){
        createRequest(listener)
                .clazz(User.class)
                .request(RetrofitClient.getService().getPersonInfo(true));
    }
}
