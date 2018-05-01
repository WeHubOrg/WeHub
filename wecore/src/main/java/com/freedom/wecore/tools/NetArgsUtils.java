package com.freedom.wecore.tools;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.ResponseBody;


/**
 * @author vurtne on 29-Apr-18.
 */
public class NetArgsUtils {

    /*双引号*/
    private static final String DOUBLE_QUOTE = "\"";
    /**
     * 提交数据的字符编码
     */
    private static final String GET_ENCODE = "utf8";


    public static String getClientUserType(Context mContext) {
        return "com.eelly.buyer".equals(mContext.getPackageName()) ? "buyer" : "seller";
    }



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
     * 将ResponseBody转换为JsonElement
     */
    public static JsonElement responseBody2JsonElement(ResponseBody responseBody) {
        JsonElement mJsonElement = null;
        try {
            String mString = new String(responseBody.bytes());
            mJsonElement = GsonConvertUtils.getJsonParser().parse(mString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mJsonElement;
    }

    public static <T> T parseJsonElement(JsonElement je, Class<T> mClass) {
        try {
            if (je.isJsonArray()) {
                List<T> list = new ArrayList<>();
                T objT;
                JsonArray jsonArray = je.getAsJsonArray();
                JsonObject jsonObject;
                int length = jsonArray.size();
                for (int i = 0; i < length; i++) {
                    jsonObject = jsonArray.get(i).getAsJsonObject();
                    if (null != jsonObject) {
                        objT = GsonConvertUtils.getGson().fromJson(jsonObject, (Class<T>) mClass);
                        list.add(objT);
                    }
                }
                //服务端那边底层,当数据为空的时候,默认是数组,这边经常有转换异常
                if (list.isEmpty()) {
                    return null;
                }
                return (T) list;
            } else if (je.isJsonObject()) {
                //如果传进来的是String,就应该是带着一个字段的数据
                if (mClass.equals(String.class)) {
                    return (T) NetArgsUtils.getStringResult(je, "");
                }
                return GsonConvertUtils.getGson().fromJson(je, (Class<T>) mClass);
            } else {
                //需要的是一个对象,但是返回的是一个字符串,此类问题也经常出现,导致崩溃
                if (mClass.equals(String.class)) {
                    return (T) je.toString();
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.v("RetrofitLog", "通用解析异常_1");
        }
        return null;
    }


    /**
     * 做字典升序排列
     *
     * @param map
     * @return
     */
    private static String doAscendOrder(Map<String, Object> map) {
        if (map == null || map.isEmpty() || map.size() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        Collection<String> keyset = map.keySet();
        List<String> list = new ArrayList<String>(keyset);
        // 对key键值按字典升序排序
        if (!keyset.contains("0"))//如果是数字键的,按数字键排序,不再按其他顺序排序
        {
            Collections.sort(list);
        }
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                builder.append("&");
            }
            builder.append(DOUBLE_QUOTE + list.get(i).trim() + DOUBLE_QUOTE);
            builder.append("=");
            Object value = map.get(list.get(i));
            if (value instanceof Map) {
                builder.append(DOUBLE_QUOTE);
                Map<String, Object> mapObject = (Map<String, Object>) value;
                builder.append(doAscendOrder(mapObject));
                builder.append(DOUBLE_QUOTE);
            } else if (value instanceof List) {
                //如果value是Lsit
                builder.append(DOUBLE_QUOTE);
                List<Object> objectList = (List<Object>) value;
                Map<String, Object> objectMap1 = new LinkedHashMap<>();
                for (int j = 0; j < objectList.size(); j++) {
                    objectMap1.put(String.valueOf(j), objectList.get(j));
                }
                builder.append(doAscendOrder(objectMap1));
                builder.append(DOUBLE_QUOTE);
            } else {
                if (value instanceof JsonElement) {
                    builder.append(DOUBLE_QUOTE).append(GsonConvertUtils.getGson().fromJson((JsonElement) value, String.class)).append(DOUBLE_QUOTE);
                } else {
                    builder.append(value);
                }
            }
        }
        return builder.toString();
    }

    /**
     * 组成完整的请求地址
     */
    public static String composeUrl(String baseUrl, HashMap<String, String> mServerParams) {
        // 检查并自动补全地址参数开始符号
        if (baseUrl.indexOf("?") == -1) {
            baseUrl += "?";
        }
        StringBuilder encodedParams = new StringBuilder(baseUrl);
        try {
            for (Map.Entry<String, String> entry : mServerParams.entrySet()) {
                encodedParams.append('&');
                encodedParams.append(URLEncoder.encode(entry.getKey(), GET_ENCODE));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(), GET_ENCODE));
            }
            return encodedParams.toString();
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("Encoding not supported: " + GET_ENCODE, ex);
        }
    }


    /**
     * 根据json字符串返回Map对象
     *
     * @param json
     * @return
     */
    public static Map<String, Object> toMap(String json) {
        return toMap(GsonConvertUtils.getJsonParser().parse(json).getAsJsonObject());
    }

    /**
     * 将JSONObjec对象转换成Map-List集合
     *
     * @param json
     * @return
     */
    public static Map<String, Object> toMap(JsonObject json) {
        Map<String, Object> map = new HashMap<String, Object>();
        Set<Map.Entry<String, JsonElement>> entrySet = json.entrySet();
        for (Iterator<Map.Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext(); ) {
            Map.Entry<String, JsonElement> entry = iter.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof JsonArray) {
                map.put((String) key, toList((JsonArray) value));
            } else if (value instanceof JsonObject) {
                map.put((String) key, toMap((JsonObject) value));
            } else {
                map.put((String) key, value);
            }
        }
        return map;
    }

    /**
     * 将JSONObjec对象转换成Map-List集合
     *
     * @param jsonArr
     * @return
     */
    public static Map<String, Object> toMap(JsonArray jsonArr) {
        Map<String, Object> map = new LinkedHashMap<>();
        for (int i = 0; i < jsonArr.size(); i++) {
            String key = String.valueOf(i);
            Object value = jsonArr.get(i);
            if (value instanceof JsonArray) {
                map.put(key, toList((JsonArray) value));
            } else if (value instanceof JsonObject) {
                map.put(key, toMap((JsonObject) value));
            } else {
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * 将JSONArray对象转换成List集合
     *
     * @param json
     * @return
     */
    public static List<Object> toList(JsonArray json) {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < json.size(); i++) {
            Object value = json.get(i);
            if (value instanceof JsonArray) {
                list.add(toList((JsonArray) value));
            } else if (value instanceof JsonObject) {
                list.add(toMap((JsonObject) value));
            } else {
                list.add(value);
            }
        }
        return list;
    }

    public static JsonElement clearDirty(JsonElement je) {
        List<String> mKeys = new ArrayList<>();
        if (je instanceof JsonArray) {
            JsonArray mJsonArrays = je.getAsJsonArray();
            for (int i = 0; i < mJsonArrays.size(); i++) {
                clearDirty(mJsonArrays.get(i));
            }
        } else {
            JsonObject mJsonObject = je.getAsJsonObject();
            for (String key : mJsonObject.keySet()) {
                JsonElement mJsonElement = mJsonObject.get(key);
                if (mJsonElement.isJsonNull()) {
                    mKeys.add(key);
                } else if (mJsonElement instanceof JsonArray) {
                    JsonArray mJsonArrays = mJsonElement.getAsJsonArray();
                    if (mJsonArrays.size() == 0) {
                        mKeys.add(key);
                    }
                } else if (TextUtils.isEmpty(mJsonElement.toString())) {
                    mKeys.add(key);
                }
            }
            for (int i = 0; i < mKeys.size(); i++) {
                mJsonObject.remove(mKeys.get(i));
            }
        }
        return je;
    }
}
