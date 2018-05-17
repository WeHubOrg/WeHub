package com.freedom.wehub.tools;


import com.freedom.wecore.bean.Events;
/**
 *  @author vurtne on 1-May-18.
 */

public class EventsFactory {
    public static final String EVENT_WATCH  = "WatchEvent";
    public static final String EVENT_CREATE = "CreateEvent";
    public static final String EVENT_FORK = "ForkEvent";
    public static final String EVENT_PUSH = "PushEvent";
    public static final String EVENT_ISSUES = "IssuesEvent";
    public static final String EVENT_ISSUES_COMMENT = "IssueCommentEvent";

    private static final String MESSAGE_WATCH  = "starred";
    private static final String MESSAGE_CREATE = "created repository";
    private static final String MESSAGE_FORK = "forked";
    private static final String MESSAGE_PUSH = "pushed to";
    private static final String MESSAGE_ISSUES_OPENED = "opened issues";
    private static final String MESSAGE_ISSUES_CLOSED = "closed issues";
    private static final String MESSAGE_ISSUES_COMMENT= "commented on issue";


    private static final String MESSAGE_TO = "to";
    private static final String MESSAGE_AT = "at";
    private static final String MESSAGE_IN = "in";


    public static final String ACTION_CLOSED = "closed";
    public static final String ACTION_OPENED = "opened";

    public static String switchMessage(Events data){
        switch (data.getType()){
            case EVENT_WATCH:
                return MESSAGE_WATCH;
            case EVENT_CREATE:
                return MESSAGE_CREATE;
            case EVENT_FORK:
                return MESSAGE_FORK;
            case EVENT_PUSH:
                return MESSAGE_PUSH;
            case EVENT_ISSUES:
                if (ACTION_CLOSED.equals(data.getPayload().getAction())){
                    return MESSAGE_ISSUES_CLOSED;
                }else if (ACTION_OPENED.equals(data.getPayload().getAction())){
                    return MESSAGE_ISSUES_OPENED;
                }
                return "";
            case EVENT_ISSUES_COMMENT:
                return MESSAGE_ISSUES_COMMENT;
            default:
                return "";
        }
    }

    public static String switchTo(String type){
        switch(type){
            case EVENT_FORK:
                return MESSAGE_TO;
            case EVENT_PUSH:
                return MESSAGE_AT;
            case EVENT_ISSUES:
            case EVENT_ISSUES_COMMENT:
                return MESSAGE_IN;
            default:
                return "";
        }
    }

    /**
     * 是否是issues 类的
     * */
    public static boolean switchIssues(String type){
        switch (type){
            case MESSAGE_ISSUES_OPENED:
            case MESSAGE_ISSUES_CLOSED:
            case MESSAGE_ISSUES_COMMENT:
                return true;
            default:
                return false;
        }
    }


    /**
     * 是否是news 类的
     * */
    public static boolean switchNews(String type){
        switch (type){
            case EVENT_WATCH:
            case EVENT_CREATE:
            case EVENT_FORK:
                return true;
            default:
                return false;
        }
    }

    /**
     * 是否是news 类的
     * */
    public static boolean switchEvents(String type){
        switch (type){
            case EVENT_PUSH:
            case EVENT_ISSUES:
            case EVENT_ISSUES_COMMENT:
                return true;
            default:
                return false;
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
        return EVENT_CREATE.equals(data.getType());
    }
}
