package com.freedom.wecore.net;


/**
 * @author vurtne on 30-Apr-18.
 */

public interface OnResponseListener<T> {

    /**
     * 执行请求回调
     * @param response response
     */
    void onResponse(Response<T> response);

}
