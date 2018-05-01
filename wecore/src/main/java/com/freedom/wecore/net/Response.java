package com.freedom.wecore.net;

import android.text.TextUtils;
import com.google.gson.JsonElement;

/**
 * @author vurtne on 30-Apr-18.
 */
public class Response<T> {

    private T mData;
    private String mError;
    private JsonElement mJsonElement;


    /**
     * 设置返回数据
     * @param t data
     */
    public void setResponse(T t) {
        mData = t;
    }

    /**
     * 获取对象数据
     * @return data
     */
    public T get(){
        return mData;
    }

    /**
     * 获取JsonElement
     * @return JsonElement
     */
    public JsonElement getJson(){
        return mJsonElement;
    }

    /**
     * 是否出现请求错误
     * @return
     */
    boolean hasError(){
        return !TextUtils.isEmpty(mError);
    }

    public String getError(){
        return mError == null?"":mError;
    }

    public void setError(String error){
        this.mError = error;
    }
}


