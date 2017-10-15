package com.firelotus.meteoritelibrary.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.firelotus.meteoritelibrary.R;

/**
 * Created by firelotus on 2017/10/14.
 */

public class DialogUtils {
    public static Dialog createProgressDialog(Context context) {
        return createProgressDialog(context, true);
    }

    public static Dialog createProgressDialog(Context context, boolean needCancle) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("请稍等 ...");
        dialog.setCancelable(needCancle);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    public static Dialog showCommonDialog(Context context, String message,
                                          DialogInterface.OnClickListener listener) {
        return new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(context.getString(R.string.dialog_positive), listener)
                .setNegativeButton(context.getString(R.string.dialog_negative), null)
                .show();
    }

    public static Dialog showConfirmDialog(Context context, String message,
                                           DialogInterface.OnClickListener listener) {
        return new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(context.getString(R.string.dialog_positive), listener)
                .show();
    }
}
