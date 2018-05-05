package com.freedom.wehub.model;


import com.freedom.wecore.bean.Events;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vurtne on 5-May-18.
 */
public class EventModel {

    private List<Events> data;

    public List<Events> getData() {
        return data == null? new ArrayList<>():data;
    }

    public void setData(List<Events> data) {
        this.data = data;
    }
}
