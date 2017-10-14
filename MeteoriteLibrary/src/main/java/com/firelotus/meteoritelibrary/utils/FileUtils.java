package com.firelotus.meteoritelibrary.utils;

import android.content.Context;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by firelotus on 2017/10/14.
 * 文件系统管理
 */

public class FileUtils {
    public static final String LOGCAT = "logs";
    public static final String DATABASE = "databases";

    /**
     * 获取应用扩展存储Cache目录
     * @param context
     * @return
     */
    public static String getDiskCacheDir(Context context) {
        return context.getExternalCacheDir().getPath()+ File.separator;
    }

    /**
     * 获取相关功能业务目录
     * @param context
     * @param dirName
     * @return
     */
    public static String getDiskFileDir(Context context, String dirName) {
        return context.getExternalFilesDir(dirName).getPath()+File.separator;
    }

    static public String getTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.US);
        String timeStamp = sdf.format(new Date());
        return timeStamp;
    }

    static public String getTimeStampForFileName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.US);
        String timeStamp = sdf.format(new Date());
        return timeStamp;
    }
}
