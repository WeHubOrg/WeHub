package com.freedom.wehub.quest;

import com.freedom.wecore.net.OnResponseListener;
import com.freedom.wecore.net.RetrofitClient;
import com.freedom.wehub.bean.AuthModel;
import com.freedom.wehub.bean.Token;

/**
 * @author vurtne on 1-May-18.
 */

public class AuthRequest extends RetrofitClient {

    /**
     * 获取token
     * */
    public void requestToken(AuthModel.AuthRequest authRequest,OnResponseListener<Token> listener){
        createRequest(listener)
                .clazz(Token.class)
                .args(authRequest)
                .append("authorizations")
                .post();
    }
}
