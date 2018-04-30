package com.freedom.wecore.net;


/**
 * Created by vurtne on 2018/4/30.
 */

public class RetrofitIterceptor {

    private static final String KEY_TOKEN = "Authorization";
    private static final String KEY_JSON = "content-type";
    private static final String KEY_JVALUE = "application/json";

//    @Override
//    public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
//        Request originalRequest = chain.request();
//        AccessToken token = NetArgsUtils.onReadAccessToken(BaseAppManager.getContext());
//        Request authorised = originalRequest.newBuilder()
//                .addHeader(KEY_JSON,KEY_JVALUE)
//                .addHeader(KEY_TOKEN, token == null ? "" : token.getAccesstoken())
//                .build();
//        return chain.proceed(authorised);
//    }
}
