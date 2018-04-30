package com.freedom.wehub.act;


import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freedom.wecore.common.WeActivity;
import com.freedom.wecore.tools.DeviceUtil;
import com.freedom.wehub.R;
import com.jakewharton.rxbinding2.widget.RxTextView;



/**
 * @author vurtne on 29-Apr-18.
 *
 * */
public class LoginActivity extends WeActivity {

    private TextView mLogoNameView;
    private EditText mAccountView;
    private EditText mPasswordView;
    private LinearLayout mParentLayout;
    private Button mLoginView;


    private boolean isAccountError;
    private boolean isPasswordError;

    @Override
    protected int contentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mLogoNameView = findViewById(R.id.tv_name);
        mParentLayout = findViewById(R.id.layout_parent);
        mAccountView = findViewById(R.id.et_account);
        mPasswordView = findViewById(R.id.et_password);
        mLoginView = findViewById(R.id.btn_login);
        DeviceUtil.setTypeface(this,mLogoNameView,"fonts/affiliation.ttf");
    }

    @Override
    protected void initStatusBar(int statusHeight) {

    }

    @Override
    protected void initEvent() {
        RxTextView.textChanges(mAccountView).subscribe(charSequence -> {
            if (isAccountError){
                isAccountError = false;
                mParentLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.color_theme));
            }
        });

        RxTextView.textChanges(mPasswordView).subscribe(charSequence -> {
            if (isPasswordError){
                isPasswordError = false;
                mParentLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.color_theme));
            }
        });

        setClick(mLoginView, o -> {
            if (TextUtils.isEmpty(mAccountView.getText().toString().trim())) {
                mParentLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.color_error));
                isAccountError = true;
                return;
            }
            if (TextUtils.isEmpty(mPasswordView.getText().toString().trim())) {
                mParentLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.color_error));
                isPasswordError = true;
                return;
            }

        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
