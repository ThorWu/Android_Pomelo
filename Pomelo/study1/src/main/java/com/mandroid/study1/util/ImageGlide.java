package com.mandroid.study1.util;

import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class ImageGlide {
    public static void load(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url) || "null".equals(url) || imageView == null) {
            Log.d("imagelib", "ImageGlide load url or imageView is empty");
            return;
        }

        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
