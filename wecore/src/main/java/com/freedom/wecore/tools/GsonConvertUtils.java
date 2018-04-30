package com.freedom.wecore.tools;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 * @author vurtne on 30-Apr-18.
 */

public class GsonConvertUtils<T>  {

    private static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    private static Gson mGson;
    private static JsonParser mJsonPar;
    private static GsonBuilder sGsonBuilder = new GsonBuilder()
            .disableHtmlEscaping()  // 解决将对象类转成Json字符串时出现\u003d的问题
            .setDateFormat(DATE_FORMAT_DEFAULT);

    static {
        mGson = new GsonBuilder().
                registerTypeAdapter(Integer.class, new IntegerDefaultAdapter())
                .serializeNulls()
                .create();
    }

    public static String toJson(Object obj) {
        if (obj == null) {
            return "";
        }
        return mGson.toJson(obj);
    }

    public static JsonElement toJsonTree(Object obj) {
        if (obj == null) {
            return null;
        }
        return mGson.toJsonTree(obj);
    }

    public static <E> E parseFromString(String string, Class<?> classz) {
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        JsonElement jsonElement = getJsonParser().parse(string);
        return (E) mGson.fromJson(jsonElement, classz);
    }

    public static Gson getGson() {
        return mGson;
    }

    public static JsonParser getJsonParser() {
        if (mJsonPar == null) {
            mJsonPar = new JsonParser();
        }
        return mJsonPar;
    }

    public static GsonBuilder getGsonBuilder() {
        return sGsonBuilder;
    }

    //这个可以解决json 带=号 会报\u003d的问题
    public static String toJsonByExcludeFields(Object obj) {
        return sGsonBuilder.excludeFieldsWithoutExposeAnnotation().serializeNulls().create().toJson(obj);
    }

    public static String toJsonByExcludeFieldsNotNull(Object obj) {
        return sGsonBuilder.excludeFieldsWithoutExposeAnnotation().create().toJson(obj);
    }

    /**
     * 从服务端返回结果里解析结果字符串，形如 {"statusCode":0,"data":{"result":"取消订单成功"}}<br>
     * 会依次尝试 result/message/msg/success 这些字段名
     *
     * @param je ResponseParser.parse 传进来的参数
     * @return 解析不了返回null
     */
    public static String getStringResult(JsonElement je) {
        String res = getStringResult(je, "result");
        if (res == null) {
            res = getStringResult(je, "message");
        }
        if (res == null) {
            res = getStringResult(je, "msg");
        }
        if (res == null) {
            res = getStringResult(je, "success");
        }
        return res;
    }

    /**
     * 从服务端返回结果里解析结果字符串，形如 {"statusCode":0,"data":{"result":"取消订单成功"}}
     *
     * @param je        ResponseParser.parse 传进来的参数
     * @param fieldName 字符串字段名
     * @return 解析不了返回null
     */
    public static String getStringResult(JsonElement je, String fieldName) {
        if (TextUtils.isEmpty(fieldName)) {
            return getStringResult(je);
        }
        if (je == null || !je.isJsonObject()) {
            return null;
        }
        try {
            JsonElement je2 = je.getAsJsonObject().get(fieldName);
            if (je2 != null) {
                return je2.getAsString();
            }
        } catch (Exception e) {
        }
        return null;
    }

    public T fromJson(String text, TypeToken<T> mTypeToken) {
        return mGson.fromJson(text, mTypeToken.getType());
    }
}
