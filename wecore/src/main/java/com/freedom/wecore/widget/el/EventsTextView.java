package com.freedom.wecore.widget.el;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

/**
 * @author vurtne on 16-May-18.
 */
public class EventsTextView extends AppCompatTextView {

    public EventsTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EventsTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTextPair(EventsPair... pairs){
        if (pairs == null || pairs.length == 0){
            return;
        }
        String text = "";
        for (EventsPair pair : pairs){
            if (!TextUtils.isEmpty(pair.getText())){
                text += " "+pair.getText();
            }
        }
        text = text.substring(1,text.length());
        SpannableStringBuilder spannableString = new SpannableStringBuilder(text);
        ForegroundColorSpan colorSpan;
        int startPosition = 0;
        int endPosition;
        for (EventsPair pair : pairs){
            if (!TextUtils.isEmpty(pair.getText())){
                startPosition += startPosition== 0?0:1;
                endPosition = startPosition + pair.getText().length();
                colorSpan = new ForegroundColorSpan(pair.getColor());
                if (pair.getListener() != null){
                    spannableString.setSpan(pair.getListener(),startPosition, endPosition, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                spannableString.setSpan(colorSpan, startPosition,endPosition,
                        Spanned.SPAN_INCLUSIVE_EXCLUSIVE
                );
                startPosition += pair.getText().length();
            }
        }
        setText(spannableString);
        setMovementMethod(LinkMovementMethod.getInstance());
    }
}
