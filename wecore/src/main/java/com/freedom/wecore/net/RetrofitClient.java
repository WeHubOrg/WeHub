package com.freedom.wecore.net;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author vurtne on 30-Apr-18.
 */

public class RetrofitClient {

    private static final Object SERVICE_LOCK = new Object();
    private static Retrofit sRetrofit;
    private static ConnectService sService;
    public RetrofitClient(){
    }

    private static Retrofit getRetrofit() {
        if (sRetrofit == null) {
            synchronized (SERVICE_LOCK){
                if (sRetrofit == null){
                    createRetrofit();
                }
            }
        }
        return sRetrofit;
    }


    public static ConnectService getService(){
        if (sService == null) {
            synchronized (SERVICE_LOCK){
                if (sService == null){
                    RetrofitClient.sService = getRetrofit().create(ConnectService.class);
                }
            }
        }
        return sService;
    }

    public static ConnectService getTokenService(String token){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .connectTimeout(30000, TimeUnit.MILLISECONDS)
                .addInterceptor(new ClientInterceptor(token))
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
        OkHttpClient client = builder.build();
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(NetConfig.BASE_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ConnectService.class);
    }

    private static void createRetrofit(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .connectTimeout(30000, TimeUnit.MILLISECONDS)
                .addInterceptor(new ClientInterceptor(null))
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
        OkHttpClient client = builder.build();
        sRetrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(NetConfig.BASE_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    protected  <T>Request<T> createRequest(OnResponseListener<T> listener) {
        return new Request<T>(listener);
    }
}
