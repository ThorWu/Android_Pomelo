package com.mandroid.study1.modules.glide;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.mandroid.imagelib.imgglide.ImageGlide;
import com.mandroid.study1.R;

public class GlideActivity extends Activity implements View.OnClickListener {

    private ImageView ivGlide;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        initViews();
    }

    private void initViews() {
        ivGlide = findViewById(R.id.iv_glide);

        findViewById(R.id.btn_single_null).setOnClickListener(this);
        findViewById(R.id.btn_single_empty_string).setOnClickListener(this);
        findViewById(R.id.btn_single_url2).setOnClickListener(this);
        findViewById(R.id.btn_single_url).setOnClickListener(this);
        findViewById(R.id.btn_single_string_null).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_single_url:
                ImageGlide.load("http://b-ssl.duitang.com/uploads/item/201505/22/20150522104236_HmwcF.jpeg", ivGlide);
                break;
            case R.id.btn_single_null:
                ImageGlide.load(null, ivGlide);
                break;
            case R.id.btn_single_string_null:
                ImageGlide.load("null", ivGlide);
                break;
            case R.id.btn_single_empty_string:
                ImageGlide.load("", ivGlide);
                break;
            case R.id.btn_single_url2:
                ImageGlide.load("http://img0.imgtn.bdimg.com/it/u=1319382472,2538609854&fm=26&gp=0.jpg", ivGlide);
                break;
            default:
                break;
        }
    }
}
