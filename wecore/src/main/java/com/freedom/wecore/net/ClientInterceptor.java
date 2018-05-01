package com.freedom.wecore.net;

import android.text.TextUtils;

import com.freedom.wecore.common.AccountManager;

import java.io.IOException;

import okhttp3.*;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author vurtne on 1-May-18.
 */

public class ClientInterceptor implements Interceptor{

    String mToken;

    public ClientInterceptor(String token){
        this.mToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        String token = TextUtils.isEmpty(this.mToken)?
                AccountManager.instance().getToken().getToken():this.mToken;
        if(!TextUtils.isEmpty(token.trim())){
            String auth = token.startsWith("Basic") ? token : "token " + token;
            request = request.newBuilder()
                    .addHeader("Authorization", auth)
                    .build();
        }
        return chain.proceed(request);
    }
}
