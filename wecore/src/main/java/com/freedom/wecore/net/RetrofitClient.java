package com.freedom.wecore.net;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author vurtne on 30-Apr-18.
 */

public class RetrofitClient {

    private static Retrofit sRetrofit;
    private Context context;

    public RetrofitClient(Context context){
        this.context = context;
    }

    public Retrofit getRetrofit() {
        if (sRetrofit == null) {
            synchronized (this){
                if (sRetrofit == null){
                    createRetrofit();
                }
            }
        }
        return sRetrofit;
    }

    private void createRetrofit(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .connectTimeout(30000, TimeUnit.MILLISECONDS)
//                .addInterceptor(new XIterceptor())
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
        OkHttpClient client = builder.build();
        sRetrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("")
                //todo
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    protected  <T>Request<T> createRequest(OnResponseListener<T> listener) {
        return new Request<T>(context, listener);
    }
}
