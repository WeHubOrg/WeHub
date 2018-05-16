package com.freedom.wecore.widget.el;

import android.support.annotation.ColorInt;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * @author vurtne on 2-May-18.
 */
public class EventsPair {

    private String text;
    @ColorInt
    private int color;
    private ClickableSpan listener;

    public EventsPair(String text) {
        this(text,-1);
    }

    public EventsPair(String text, int color) {
        this(text,-1,null);
    }

    public EventsPair(String text, int color, ClickableSpan listener) {
        this.text = text;
        this.color = color;
        this.listener = listener;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public ClickableSpan getListener() {
        return listener;
    }

    public void setListener(ClickableSpan listener) {
        this.listener = listener;
    }
}
