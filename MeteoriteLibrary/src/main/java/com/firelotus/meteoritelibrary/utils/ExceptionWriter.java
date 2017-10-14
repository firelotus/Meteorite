package com.firelotus.meteoritelibrary.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by firelotus on 2017/10/14.
 * 异常捕获写入本地
 */

public class ExceptionWriter {
    private Throwable exception;
    private Context mContext;

    public ExceptionWriter(Throwable ex, Context c) {
        this.exception = ex;
        mContext = c;
    }

    public void saveStackTraceToSD() {
        try {
            FileOutputStream fos = getExceptionFileStream();
            StringBuilder sb = new StringBuilder();
            sb.append("app name = " + AppUtils.getAppName(mContext)+ "\n");
            sb.append("app pkg = " + mContext.getPackageName()+ "\n");
            sb.append("android sdk version = " + Build.VERSION.SDK_INT + "\n");
            sb.append("version name = " + Build.VERSION.RELEASE + "\n");
            sb.append("brand = " + Build.BRAND + "\n");
            sb.append("phoneType = " + Build.MODEL + "\n");
            sb.append("error occured Time = " + FileUtils.getTimeStamp() + "\n\n");

            StringWriter writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            exception.printStackTrace(printWriter);
            Throwable cause = exception.getCause();
            while (cause != null) {
                cause.printStackTrace(printWriter);
                cause = cause.getCause();
            }
            printWriter.close();
            String result = writer.toString();
            sb.append(result);
            try {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    fos.write(sb.toString().getBytes());
                    fos.close();
                }
            } catch (Exception e) {  }

        } catch (Exception excep) {
            excep.printStackTrace();
        }
    }

    private FileOutputStream getExceptionFileStream() throws FileNotFoundException {
        File myDir = new File(FileUtils.getDiskFileDir(mContext,FileUtils.LOGCAT));
        File file = new File(myDir, FileUtils.getTimeStampForFileName() + ".txt");
        if (file.exists()){
            file.delete();
        }
        FileOutputStream out = new FileOutputStream(file);
        return out;
    }
}
