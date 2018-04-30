package com.freedom.wehub;

import android.content.Context;

/**
 * @author vurtne on 29-Apr-18.
 *
 * */
public class T {

//    private class ApiDemo extends RetrofitClient {
//
//        private ApiDemo(Context mContext) {
//            super(mContext);
//        }
//
//        private void onRequestToken(){
//            newRequest(new ApiListener<AccessToken>() {
//                @Override
//                public void onResponse(ApiResponse<AccessToken> response) {
//                    AccessToken token = response.get();
//                    token.setLifeTime(System.currentTimeMillis()+(Long.parseLong(token.getExpires_in())*1000) +"");
//                    NetArgsUtils.onWriteAccessToken(token);
//                    ToastUtil.show(context,response.toString());
//                }
//
//            })
//                    //设置解析类型
//                    .setClass(AccessToken.class)
//                    //执行自定义的请求
//                    .onRequest(RetrofitClient.getService().getToken("password","myawesomeapp",
//                            "abc123","molimoq","123456",null));
//        }
//
//        private void onRequestAuth(){
//            newRequest(new ApiListener<DataRequest.Response>() {
//                @Override
//                public void onResponse(ApiResponse<DataRequest.Response> response) {
//                    ToastUtil.show(context,response.get().getString());
//                }
//
//            })
//                    //设置解析类
//                    .setClass(DataRequest.Response.class)
//                    //设置请求参数
//                    .setArgs(new DataRequest.Request())
//                    //拼接请求地址
//                    .append("user/user/getUserByPassword")
//                    //执行请求
//                    .onRequest();
//
//
//        }
//
//    }
}
