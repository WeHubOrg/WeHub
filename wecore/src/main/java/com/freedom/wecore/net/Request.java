package com.freedom.wecore.net;

import android.text.TextUtils;

import com.freedom.wecore.tools.GsonConvertUtils;
import com.freedom.wecore.tools.LogUtil;
import com.freedom.wecore.tools.NetArgsUtils;
import com.google.gson.JsonElement;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author vurtne on 30-Apr-18.
 */

public class Request<T> {

    private final static String TAG = "Request";
    /**
     * 如果对象是一个类
     **/
    private Class mClass;
    /**
     * 模型解析器,如果不想由默认方式进行解析,可以传解析器自己解析
     **/
    private ResponseParser<T> mParser;
    /**
     * 请求是否取消了
     **/
    private boolean isCancel = false;
    private OnResponseListener<T> mListener;
    private CompositeDisposable mCompositeDisposable;

    private String mAppendPath;

    private Object mRequestData;
    private Request<T> mRefreshRequest;


    public Request(OnResponseListener<T> listener) {
        mCompositeDisposable = new CompositeDisposable();
        mListener = listener;
    }

    /**
     * 设置请求参数
     * */
    public Request<T> args(Object args) {
        if (args != null) {
            mRequestData = args;
        }
        return this;
    }

    /**
     * 拼接地址
     * 替代了久的请求方法的 设置 服务和方法
     */
    public Request<T> append(String url){
        mAppendPath = url;
        mAppendPath = mAppendPath == null?"":mAppendPath;
        return this;
    }


    /**
     * 设置解析类
     *
     * @param clazz  用来解析的class;
     */
    public Request<T> clazz(Class clazz) {
        mClass = clazz;
        return this;
    }


    public Request<T> parser(ResponseParser<T> parser){
        mParser = parser;
        return this;
    }

    /**
     * get请求
     */
    public void get() {
        request(RetrofitClient.getService().get(getPath(),mRequestData));
    }


    /**
     * post请求
     */
    public void post(){
        request(RetrofitClient.getService().post(getPath(),mRequestData));
    }

    /**
     * 请求
     * */
    public void request(Observable<retrofit2.Response<JsonElement>> observable){
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()){
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(onParseMap(observable.subscribeOn(Schedulers.io()))
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        onCreateSuccessConsumer(),
                        onCreateErrorConsumer()));
    }


    private Observable<Response<T>> onParseMap(Observable<retrofit2.Response<JsonElement>> observable) {
        return observable.map(response -> {
            Response<T> tResponse = new Response<>();
            ApiResponseModel<T> model = new ApiResponseModel<>();
            model.setData(response.body() == null ? "":response.body().toString());
            model.setStatus(response.code());
            model.setInfo(response.message());
            try {
                onResponse(tResponse, model, false);
                return tResponse;
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.v(TAG, "通用解析异常_2");
            }
            return tResponse;
        });
    }


    private Response<T> onResponse(ApiResponseModel<T> responseModel, boolean isCache) throws Exception {
        return onResponse(new Response<T>(), responseModel, isCache);
    }

    /**
     * 在这进行数据的解析,包括缓存数据或者服务端返回的数据
     */
    private Response<T> onResponse(Response<T> response, ApiResponseModel<T> responseModel, boolean isCache) throws Exception {
        if (responseModel == null) {
            return response;
        }
        String dataStr = responseModel.getData();
        JsonElement je = GsonConvertUtils.getJsonParser().parse(dataStr);
        T bean = null;
        if (mParser != null) {
            bean = mParser.parse(je);
        } else {
            bean = (T) NetArgsUtils.parseJsonElement(je, mClass);
        }
        response.setResponse(bean);
        return response;
    }

    /**
     * 创建成功的回调
     * */
    private Consumer<Response<T>> onCreateSuccessConsumer(){
        return response -> {
            if (!TextUtils.isEmpty(response.getError())) {
                LogUtil.v("RetrofitLog", mRequestData.toString() + ":" + response.getError());
            }
            if (mListener != null && !isCancel) {
                mListener.onResponse(response);
            }
        };
    }

    /**
     * 创建失败的回调
     * */
    private Consumer<Throwable> onCreateErrorConsumer(){
        return throwable -> {
            LogUtil.d(TAG,throwable.toString());
            Response<T> apiResponse = new Response<>();
            apiResponse.setError(throwable.toString() == null?
                    throwable.getClass().getSimpleName():throwable.toString());
            if (mListener != null){
                mListener.onResponse(apiResponse);
            }
        };
    }

    public void cancel() {
        if (mCompositeDisposable != null){
            if (!mCompositeDisposable.isDisposed()){
                mCompositeDisposable.dispose();
            }
            mCompositeDisposable = null;
        }
        if (mRefreshRequest != null){
            mRefreshRequest.cancel();
            mRefreshRequest = null;
        }
        isCancel = true;
    }

    private String getPath(){
        return NetConfig.BASE_HOST + "/" +mAppendPath;
    }

}

