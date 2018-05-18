package com.freedom.wecore.tools;

import com.freedom.wecore.event.IEvent;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * @author Vurtne on 9-Apr-18.
 *
 */

public class RxBus {

    private static volatile RxBus sInstance;

    private final FlowableProcessor<Object> mBus;

    private RxBus(){
        mBus = PublishProcessor.create().toSerialized();
    }

    public FlowableProcessor getProcessor(){
        return mBus;
    }

    public static RxBus get(){
        if (sInstance == null){
            synchronized (RxBus.class){
                if (sInstance == null){
                    sInstance = new RxBus();
                }
            }
        }
        return sInstance;
    }

    /**
     * 发送一个新的事件，所有订阅此事件的订阅者都会收到
     * */
    public void post(IEvent obj){
        mBus.onNext(obj);
    }

    public <T> Flowable<T> add(Class<T> tClass) {
        return mBus.ofType(tClass);
    }

    public boolean hasSubscribers() {
        return mBus.hasSubscribers();
    }


}
