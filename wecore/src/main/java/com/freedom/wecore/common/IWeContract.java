package com.freedom.wecore.common;

import android.content.Context;
import android.support.annotation.Nullable;

/**
 * @author vurtne on 1-May-18.
 */

public class IWeContract {

    interface View {

    }

    interface Presenter<V extends IWeContract.View>{
        @Nullable
        Context getContext();
    }
}
