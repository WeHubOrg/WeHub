package com.freedom.wehub.tools;


import com.freedom.wecore.bean.Events;

public class EventsFactory {
    public static final String EVENT_WATCH  = "WatchEvent";
    public static final String EVENT_CREATE = "CreateEvent";
    public static final String EVENT_FORK = "ForkEvent";

    private static final String MESSAGE_WATCH  = "starred";
    private static final String MESSAGE_CREATE = "created repository";
    private static final String MESSAGE_FORK = "forked";
    private static final String MESSAGE_TO = "to";

    public static String switchMssage(String type){
        switch (type){
            case EVENT_WATCH:
                return MESSAGE_WATCH;
            case EVENT_CREATE:
                return MESSAGE_CREATE;
            case EVENT_FORK:
                return MESSAGE_FORK;
        }
        return "";
    }

    public static String switchTo(String type){
        if (EVENT_FORK.equals(type)){
            return MESSAGE_TO;
        }else {
            return "";
        }
    }

    public static String switchDestinationAddress(Events data){
        if (EVENT_FORK.equals(data.getType())){
            return  data.getActor().getLogin() + data.getRepo().getName().substring(data.getRepo().
                    getName().indexOf("/"),data.getRepo().getName().length());
        }else {
            return "";
        }
    }

    /** 是否是创建事件 */
    public static boolean switchCreate(Events data){
        return MESSAGE_CREATE.equals(data.getType());
    }
}
