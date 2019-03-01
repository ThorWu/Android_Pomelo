package com.mandroid.study1.modules.glide;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.mandroid.study1.R;
import com.mandroid.study1.beans.GlideItem;
import com.mandroid.study1.modules.glide.widget.GlideListAdapter;
import com.mandroid.study1.util.glide.ImageGlide;

import java.util.ArrayList;
import java.util.List;

public class GlideActivity extends Activity implements View.OnClickListener {

    private ImageView ivGlide;
    private ListView lvGlide;
    private GlideListAdapter mAdapter;
    private String[] urlArr = {"http://b-ssl.duitang.com/uploads/item/201602/02/20160202205548_HjJZQ.thumb.700_0.jpeg", "http://p0.ifengimg.com/pmop/2018/0709/660AFB65BF449E972ACD9C1DDE52E2524A7E9E0C_size42_w640_h640.jpeg", "http://www.sinaimg.cn/dy/slidenews/2_img/2012_20/786_696987_218419.jpg"};
    private List<GlideItem> datas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        initViews();

        setDatas();
    }

    private void initViews() {
        ivGlide = findViewById(R.id.iv_glide);

        findViewById(R.id.btn_single_null).setOnClickListener(this);
        findViewById(R.id.btn_single_empty_string).setOnClickListener(this);
        findViewById(R.id.btn_single_url2).setOnClickListener(this);
        findViewById(R.id.btn_single_url).setOnClickListener(this);
        findViewById(R.id.btn_single_string_null).setOnClickListener(this);

        lvGlide = findViewById(R.id.lv_glide);
        mAdapter = new GlideListAdapter();
        lvGlide.setAdapter(mAdapter);

    }

    private void setDatas() {
        for (String url : urlArr) {
            GlideItem item = new GlideItem();
            item.url = url;
            datas.add(item);
        }

        for (GlideItem item : datas) {
            Log.d("Glide", "item url:" + item.url);
        }

        mAdapter.setDatas(datas);
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
