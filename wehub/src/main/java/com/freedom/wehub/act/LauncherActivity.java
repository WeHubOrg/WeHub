package com.freedom.wehub.act;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.freedom.wehub.R;

/**
 * @author vurtne on 29-Air-18.
 *
 * */
public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/affiliation.ttf");
        ((TextView)findViewById(R.id.tv_name)).setTypeface(typeface);
    }
}
