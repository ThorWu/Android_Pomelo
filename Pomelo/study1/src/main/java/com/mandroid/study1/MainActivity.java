package com.mandroid.study1;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mandroid.study1.beans.Child;
import com.mandroid.study1.beans.Father;
import com.mandroid.study1.modules.drag.DragAndDropActivity;
import com.mandroid.study1.modules.gallery.GalleryActivity;
import com.mandroid.study1.modules.glide.GlideActivity;
import com.mandroid.study1.modules.viewmodelsample.ViewModelSampleActivity;
import com.mandroid.study1.util.toast.DsxUiToast;
import com.mandroid.study1.util.toast.DsxUiToastHelper;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            String test = savedInstanceState.getString("extra_test");
            Log.d(TAG, "[onCreate]restore extra_test:" + test);
        }
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent goStudy2 = new Intent();
                //action
//                goStudy2.setAction("com.mandroid.study2.enter");

                //componentName
                ComponentName componentName = new ComponentName("com.mandroid.study2",
                        "com.mandroid.study2.EntertainmentActivity");
                goStudy2.setComponent(componentName);

                if (goStudy2.resolveActivity(getPackageManager()) != null) {
                    startActivity(goStudy2);
                }

//                Intent intent = new Intent("com.ryg.charpter_1.c");
//                //intent.setClass(MainActivity.this, SecondActivity.class);
//                intent.putExtra("time", System.currentTimeMillis());
//                intent.addCategory("com.ryg.category.c");
//                intent.setDataAndType(Uri.parse("file://abc"), "text/plain");
//                startActivity(intent);
            }
        });

        initViews();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent, time=" + intent.getLongExtra("time", 0));
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged, newOrientation:" + newConfig.orientation);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        outState.putString("extra_test", "test");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //Log.d(TAG, "onRestoreInstanceState");
        String test = savedInstanceState.getString("extra_test");
        Log.d(TAG, "[onRestoreInstanceState]restore extra_test:" + test);
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button_glide:
                goActivity(GlideActivity.class);
                break;
            case R.id.button_gallery:
                goActivity(GalleryActivity.class);
                break;
            case R.id.button_drag:
                goActivity(DragAndDropActivity.class);
                break;
            case R.id.button_toast:
                DsxUiToastHelper.getInstance().showLongPositiveToast(this, "测试自定义Toast", DsxUiToast.TOP_50);
                break;
            case R.id.btn_clone:
                handleCloneClick();
                break;
            case R.id.btn_view_model:
                goActivity(ViewModelSampleActivity.class);
                break;
            default:
                break;
        }
    }


    private void initViews() {
        findViewById(R.id.button_glide).setOnClickListener(this);
        findViewById(R.id.button_gallery).setOnClickListener(this);
        findViewById(R.id.button_drag).setOnClickListener(this);
        findViewById(R.id.button_toast).setOnClickListener(this);
        findViewById(R.id.btn_clone).setOnClickListener(this);
        findViewById(R.id.btn_view_model).setOnClickListener(this);
    }

    private void goActivity(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    private void handleCloneClick() {
        Father father = new Father();
        father.age = 26;
        father.name = "张三";
        father.child = new Child();
        father.child.name = "小张三";
        father.child.age = 2;

        Father fatherClone = (Father) father.clone();

        Log.d("clone", "father hashCode:" + father.hashCode());
        Log.d("clone", "fatherClone hashCode:" + fatherClone.hashCode());
        Log.d("clone", "fatherClone == father:" + (fatherClone == father));
        Log.d("clone", "fatherClone.name == father.name:" + (fatherClone.name == father.name));
        Log.d("clone", "fatherClone.age == father.age:" + (fatherClone.age == father.age));

        Log.d("clone", "father.child hashCode:" + father.child.hashCode());
        Log.d("clone", "fatherClone.child hashCode:" + fatherClone.child.hashCode());
        Log.d("clone", "fatherClone.child == father.child:" + (fatherClone.child == father.child));

    }
}

