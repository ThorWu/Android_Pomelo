package com.mandroid.study1.modules.drag;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mandroid.study1.R;

public class DragAndDropActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);

        initViews();
    }

    private void initViews() {
    }

}
