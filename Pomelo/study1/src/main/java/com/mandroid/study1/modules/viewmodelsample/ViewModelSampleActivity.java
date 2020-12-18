package com.mandroid.study1.modules.viewmodelsample;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mandroid.study1.R;

public class ViewModelSampleActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model_sample);
//        MyViewModel myViewModel =  new ViewModelProvider(this).get(MyViewModel.class);
    }
}
