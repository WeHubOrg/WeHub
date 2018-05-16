package com.freedom.wecore.widget.el;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freedom.wecore.R;
import com.freedom.wecore.tools.DeviceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vurtne on 2-May-18.
 */
public class EventsLayout extends LinearLayout {

    private Context context;
    private LinearLayout mTopLayout;
    private LinearLayout mBottomLayout;
    private List<AppCompatTextView> views = new ArrayList<>();
    private EventsPair[] pairs;

    public EventsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EventsLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init(){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.view_events_layout, this);
        mTopLayout = findViewById(R.id.layout_top);
        mBottomLayout = findViewById(R.id.layout_bottom);

        if (pairs == null || pairs.length == 0){
            return;
        }
        LinearLayout.LayoutParams params;
        for (int i = 0;i<pairs.length;i++){
            final EventsPair pair = pairs[i];
            final AppCompatTextView textView = new AppCompatTextView(context);
            params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            if ( i > 0){
                params.setMargins(DeviceUtil.dip2Px(context,3),0,0,0);
            }
            textView.setLayoutParams(params);
            textView.setTextSize(R.dimen.text_14);
            textView.setText(pair.getText());
            textView.setTextColor(pair.getColor());
            if (pairs[i].getListener() != null){
                textView.setOnClickListener(v -> pair.getListener().onClick(textView));
            }
            mTopLayout.addView(textView);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setPair(EventsPair... pairs){
        this.pairs = pairs;

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
