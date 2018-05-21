package com.freedom.wecore.net;

import android.support.annotation.NonNull;

import com.google.gson.JsonElement;


import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @author vurtne on 30-Apr-18.
 */
@SuppressWarnings({"unused"})
public interface ConnectService {

    /**
     * fetch data by rule id
     *
     * @param path 拼接地址
     * @param dataJson 传参 是以json格式
     * @return 返回  Observable
     */
    @GET()
    @Headers("Accept: application/json")
    Observable<Response<JsonElement>> get(@Url String path, @Body Object dataJson);


    /**
     * fetch data by rule id
     *
     * @param path 拼接地址
     * @param dataJson 传参 是以json格式
     * @return 返回  Observable
     */
    @POST()
    @Headers("Accept: application/json")
    Observable<Response<JsonElement>> post(@Url String path, @Body Object dataJson);


    /**
     * 登陆
     * @param forceNetWork forceNetWork
     * @return Response
     * */
    @GET("user")
    @Headers("Accept: application/json")
    Observable<Response<JsonElement>> getPersonInfo(@Header("forceNetWork") boolean forceNetWork);


    /**
     * 获取news
     * @param forceNetWork forceNetWork
     * @param user user
     * @param page page
     * @return Response
     * */
    @NonNull
    @GET("users/{user}/received_events")
    Observable<Response<JsonElement>> getNewsEvent(
            @Header("forceNetWork") boolean forceNetWork,
            @Path("user") String user,
            @Query("page") int page
    );


    /**
     * 获取Events
     * @param forceNetWork forceNetWork
     * @param user user
     * @param page page
     * @return Response
     */
    @NonNull
    @GET("users/{user}/events")
    Observable<Response<JsonElement>> getUserEvents(
            @Header("forceNetWork") boolean forceNetWork,
            @Path("user") String user,
            @Query("page") int page
    );


    @NonNull @GET("users/{user}")
    Observable<Response<JsonElement>> getUserInfo(
            @Header("forceNetWork") boolean forceNetWork,
            @Path("user") String user
    );


}
