package com.freedom.wecore.net;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 服务端统一的返回数据格式<br>
 * <p>
 * import static android.R.attr.author;
 *
 * @author 林钊平
 */
public class ApiResponseModel<T> implements Serializable {

    /** */
    private static final long serialVersionUID = 1L;

    /**
     * 返回的状态码<br>
     * 200 为正常返回，其他则为服务端返回错误
     */
    @SerializedName(value = "status", alternate = {"statusCode"})
    private int status = 0;

    /**
     * 服务端错误时返回的错误信息
     */
    @SerializedName(value = "info", alternate = {"message"})
    private String info = "";

    /**
     * 返回的数据
     */
    private Retval retval;
    private String md5Key;
    /**
     * 是否缓存的标识
     * 缓存使用.该数据只由客户端设置,不应该由服务端返回
     */
    private boolean isCache = false;
    /**
     * 3des加密key
     * 缓存使用.该数据只由客户端设置,不应该由服务端返回
     */
    private String apiEncryptKey;
    /**
     * 3des加密向量
     * 缓存使用.该数据只由客户端设置,不应该由服务端返回
     */
    private String apiEncryptIv;

    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
    }

    /**
     * @see #status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @see #status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @see #info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @see #info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * {@link Retval#data}
     */
    public String getData() {
        return retval == null ? "" : retval.data;
    }

    /**
     * {@link Retval#data}
     */
    public void setData(String data) {
        if (retval == null) {
            retval = new Retval();
        }
        retval.data = data;
    }

    /**
     * {@link Retval#signature}
     */
    public String getSignature() {
        return retval == null ? "" : retval.signature;
    }

    /**
     * @return
     */
    public boolean getIsCache() {
        return isCache;
    }

    /**
     * @param isCache
     */
    public void setIsCache(boolean isCache) {
        this.isCache = isCache;
    }

    /**
     * @return
     */
    public String getApiEncryptKey() {
        return apiEncryptKey;
    }

    /**
     * @param apiEncryptKey
     */
    public void setApiEncryptKey(String apiEncryptKey) {
        this.apiEncryptKey = apiEncryptKey;
    }

    /**
     * @return
     */
    public String getApiEncryptIv() {
        return apiEncryptIv;
    }

    /**
     * @param apiEncryptIv
     */
    public void setApiEncryptIv(String apiEncryptIv) {
        this.apiEncryptIv = apiEncryptIv;
    }

    public static class Retval implements Serializable {

        /**  */
        private static final long serialVersionUID = 1L;

        /**
         * 加密具体的数据<br>
         * 返回是加密数据，加密算法为3DES，模式为CBC模式
         */
        private String data = "";

        /**
         * 签名信息
         */
        private String signature = "";
    }
}
