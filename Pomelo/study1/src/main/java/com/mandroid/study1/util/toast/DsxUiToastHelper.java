package com.mandroid.study1.util.toast;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.IntDef;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


import com.mandroid.study1.R;
import com.mandroid.study1.util.Tools;
import com.mandroid.study1.util.icon.IconView;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xueqingqing on 2019/12/5 2:47 PM
 * Email: qingqing.xue@ifchange.com
 */
public class DsxUiToastHelper {
    private static class Holder {
        private static DsxUiToastHelper INSTANCE = new DsxUiToastHelper();
    }

    public static DsxUiToastHelper getInstance() {
        return Holder.INSTANCE;
    }

    @Documented
    @IntDef(value = {DsxUiToast.TOP_30, DsxUiToast.TOP_40, DsxUiToast.TOP_50})
    @Retention(RetentionPolicy.SOURCE)
    @interface PositionMode {
    }

    private Toast mLastToast;

    private DsxUiToastHelper() {
    }

    public Toast showShortPositiveToast(Context context, String info, @PositionMode int position) {
        return showShortPositiveToast(context, info, null, position);
    }

    public Toast showLongPositiveToast(Context context, String info, @PositionMode int position) {
        return showLongPositiveToast(context, info, null, position);
    }

    public Toast showShortPositiveToast(Context context, String info, String icon, @PositionMode int position) {
        return showPositiveToast(context, info, icon, position, Toast.LENGTH_SHORT);
    }

    public Toast showLongPositiveToast(Context context, String info, String icon, @PositionMode int position) {
        return showPositiveToast(context, info, icon, position, Toast.LENGTH_LONG);
    }

    public Toast showShortNegativeToast(Context context, String info, @PositionMode int position) {
        return showShortNegativeToast(context, info, null, position);
    }

    public Toast showLongNegativeToast(Context context, String info, @PositionMode int position) {
        return showLongNegativeToast(context, info, null, position);
    }

    public Toast showShortNegativeToast(Context context, String info, String icon, @PositionMode int position) {
        return showNegativeToast(context, info, icon, position, Toast.LENGTH_SHORT);
    }

    public Toast showLongNegativeToast(Context context, String info, String icon, @PositionMode int position) {
        return showNegativeToast(context, info, icon, position, Toast.LENGTH_LONG);
    }

    private Toast showPositiveToast(Context context, String info, String icon, @PositionMode int position, int duration) {
        View content = buildPositiveToastContent(context, info, icon);
        return showToast(context, content, position, duration);
    }

    private Toast showNegativeToast(Context context, String info, String icon, @PositionMode int position, int duration) {
        View content = buildNegativeToastContent(context, info, icon);
        return showToast(context, content, position, duration);
    }

    private Toast showToast(Context context, View content, int position, int duration) {
        if (mLastToast != null) {
            mLastToast.cancel();
        }
        Toast toast = new Toast(context);
        toast.setDuration(duration);
        toast.setView(content);
        toast.setGravity(Gravity.TOP, 0, Tools.dip2px(context, position));
//        hookToast(toast);
        toast.show();
        mLastToast = toast;
        return toast;
    }

    private View buildPositiveToastContent(Context context, String info, String icon) {
        View view = buildToastContent(context, info, icon);
        view.setBackgroundResource(R.drawable.dsx_ui_kit_bg_toast_positive);
        return view;
    }

    private View buildNegativeToastContent(Context context, String info, String icon) {
        View view = buildToastContent(context, info, icon);
        view.setBackgroundResource(R.drawable.dsx_ui_kit_bg_toast_negative);
        return view;
    }

    private View buildToastContent(Context context, String info, String icon) {
        View view = LayoutInflater.from(context).inflate(R.layout.dsx_ui_kit_toast, null);
        IconView iconView = view.findViewById(R.id.dsx_ui_kit_toast_icon);
        if (TextUtils.isEmpty(icon)) {
            iconView.setVisibility(View.GONE);
        } else {
            iconView.setVisibility(View.VISIBLE);
            iconView.setText(icon);
        }
        TextView infoView = view.findViewById(R.id.dsx_ui_kit_toast_text);
        infoView.setText(info);
        return view;
    }

    private static void hookToast(Toast toast) {
        int sdk = Build.VERSION.SDK_INT;
        if (sdk >= Build.VERSION_CODES.N && sdk < Build.VERSION_CODES.O) {
            try {
                Field tNField = toast.getClass().getDeclaredField("mTN");
                if (tNField == null) {
                    return;
                }
                tNField.setAccessible(true);
                Object TN = tNField.get(toast);
                if (TN == null) {
                    return;
                }
                Field handlerField = TN.getClass().getDeclaredField("mHandler");
                if (handlerField == null) {
                    return;
                }
                handlerField.setAccessible(true);
                handlerField.set(TN, new ProxyTNHandler(TN));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Exception e) {
                //catch everything
            }
        }
    }

    private static class ProxyTNHandler extends Handler {
        private Object tnObject;
        private Method handleShowMethod;
        private Method handleHideMethod;

        ProxyTNHandler(Object tnObject) {
            this.tnObject = tnObject;
            try {
                this.handleShowMethod = tnObject.getClass().getDeclaredMethod("handleShow", IBinder.class);
                this.handleShowMethod.setAccessible(true);
                this.handleHideMethod = tnObject.getClass().getDeclaredMethod("handleHide");
                this.handleHideMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: {
                    //SHOW
                    IBinder token = (IBinder) msg.obj;
                    if (handleShowMethod != null) {
                        try {
                            handleShowMethod.invoke(tnObject, token);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (WindowManager.BadTokenException e) {
                            //显示Toast时添加BadTokenException异常捕获
                            e.printStackTrace();
                        } catch (Exception e) {
                            //catch everything
                        }
                    }
                    break;
                }

                case 1: {
                    //HIDE
                    if (handleHideMethod != null) {
                        try {
                            handleHideMethod.invoke(tnObject);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
                case 2: {
                    //CANCEL
                    if (handleHideMethod != null) {
                        try {
                            handleHideMethod.invoke(tnObject);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }

            }
            super.handleMessage(msg);
        }
    }
}
