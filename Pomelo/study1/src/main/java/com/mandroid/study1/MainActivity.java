package com.mandroid.study1;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mandroid.study1.modules.gallery.GalleryActivity;
import com.mandroid.study1.modules.glide.GlideActivity;

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
            default:
                break;
        }
    }


    private void initViews() {
        findViewById(R.id.button_glide).setOnClickListener(this);
        findViewById(R.id.button_gallery).setOnClickListener(this);
    }

    private void goActivity(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}

