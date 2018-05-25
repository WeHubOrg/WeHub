package com.freedom.wehub.model;


import com.freedom.wecore.bean.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vurtne on 25-May-18.
 */
public class RepositoryModel {

    private List<Repository> data;

    public List<Repository> getData() {
        return data == null? new ArrayList<>():data;
    }

    public void setData(List<Repository> data) {
        this.data = data;
    }
}
