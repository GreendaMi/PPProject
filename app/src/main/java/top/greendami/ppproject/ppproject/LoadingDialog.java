package top.greendami.ppproject.ppproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


/**
 * Created by GreendaMi on 2017/2/21.
 */

public class LoadingDialog extends Dialog {

    public static LoadingDialog mLoadingDialog;
    public static LoadingDialog mLoadingNoCancelDialog;

    private static TextView tvMsg,tvUploadFileMsg;


    public LoadingDialog(Context context) {
        super(context, R.style.Theme_Dialog_Transparent);
        /**设置对话框背景透明*/
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.loading);
        setCanceledOnTouchOutside(false);
    }

    public LoadingDialog(Context context, boolean cancelable) {
        super(context, R.style.Theme_Dialog_Transparent);
        /**设置对话框背景透明*/
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.loading);
        setCanceledOnTouchOutside(cancelable);
        setCancelable(cancelable);
    }
    public LoadingDialog(Context context, String msg, boolean cancelable) {
        super(context, R.style.Theme_Dialog_Transparent);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCanceledOnTouchOutside(cancelable);
        setCancelable(cancelable);
        View view = LayoutInflater.from(context).inflate(R.layout.loading_with_text, null);
        setContentView(view);
        tvUploadFileMsg = view.findViewById(R.id.tv_msg);
    }
    public LoadingDialog(Context context, String msg) {
        super(context, R.style.Theme_Dialog_Transparent);
        /**设置对话框背景透明*/
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCanceledOnTouchOutside(false);
        View view = LayoutInflater.from(context).inflate(R.layout.loading_with_text, null);
        setContentView(view);
        tvMsg = view.findViewById(R.id.tv_msg);
    }

    public static void showNoCancelDialog(Context context) {
        if (context == null) {
            return;
        }
        if (mLoadingNoCancelDialog == null) {
            mLoadingNoCancelDialog = new LoadingDialog(context, false);
        }
        if (!((Activity) context).isFinishing()) {
            try {
                mLoadingNoCancelDialog.show();
            }catch (Exception e){
                e.getMessage();
            }
        }
    }

    public static void showDialog(Context context) {
        if (context == null) {
            return;
        }
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(context);
            mLoadingDialog.setOnDismissListener(new OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    mLoadingDialog=null;
                }
            });
        }
        if (!((Activity) context).isFinishing()) {
            try {
                mLoadingDialog.show();
            }catch (Exception e){
                e.getMessage();
            }
        }
    }

    public static void dismissDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    public static void dismissCancelDialog() {
        if (mLoadingNoCancelDialog != null && mLoadingNoCancelDialog.isShowing()) {
            mLoadingNoCancelDialog.dismiss();
            mLoadingNoCancelDialog = null;
        }
    }


}
