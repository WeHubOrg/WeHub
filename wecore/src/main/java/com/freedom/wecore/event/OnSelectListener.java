package com.freedom.wecore.event;

import java.util.List;

public interface OnSelectListener<T> {

    //添加
    int TYPE_ADD = 1;
    //删除
    int TYPE_REMOVE = 2;
    //选择
    int TYPE_SELECT = 3;
    //点击
    int TYPE_CLICK = 4;
    //other
    int TYPE_OTHER = 5;

    void onOperation(List<T> mData, T mItem, int position, int type);

}
