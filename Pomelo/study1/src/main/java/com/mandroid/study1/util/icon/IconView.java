
package com.mandroid.study1.util.icon;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by xueqingqing on 2019/12/5 1:55 PM
 * Email: qingqing.xue@ifchange.com
 */
public class IconView extends AppCompatTextView {

    private static Typeface DEF_ICON;

    public static void setDefaultIconFont(Typeface typeface) {
        DEF_ICON = typeface;
    }

    public IconView(Context context) {
        this(context, null);
    }

    public IconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        if (DEF_ICON != null) {
            setTypeface(DEF_ICON);
        }
    }
}
