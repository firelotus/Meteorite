package com.firelotus.meteoritelibrary.toast;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast管理器
 * Created by firelotus on 2017/10/14.
 */

public class MToast {
    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message)
    {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message)
    {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message)
    {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message)
    {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration)
    {
        Toast.makeText(context, message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, int message, int duration)
    {
        Toast.makeText(context, message, duration).show();
    }
}
