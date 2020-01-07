package jrh.library.common.widgets;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.dialog.LoadingDialog;
import com.xuexiang.xui.widget.dialog.MiniLoadingDialog;

import java.lang.ref.WeakReference;

import aly.library.common.base.R;
import jrh.library.common.utils.ActivityUtil;
import jrh.library.common.utils.StringUtil;

/**
 * desc:
 * Created by jiarh on 2018/8/8 15:53.
 */

public class ProgressLoadingDialog {

    private WeakReference<Activity> mContext;
    private MiniLoadingDialog mLoadingDialog;
    private View view;

    private boolean isShow;

    public ProgressLoadingDialog(Activity context) {
        mContext = new WeakReference<Activity>(context);
        mLoadingDialog = WidgetUtils.getMiniLoadingDialog(mContext.get());
        mLoadingDialog.setCancelable(true);
        mLoadingDialog.setCanceledOnTouchOutside(true);
    }

    public void show() {
        show("");
    }

    public void show(String msg) {
        if (ActivityUtil.isActivityFinished(mContext.get()) || isShowing()) {
            return;
        } else {
            mLoadingDialog.show();
            isShow= true;
        }

    }

    public void hide() {
        if (isShowing()) {
            mLoadingDialog.dismiss();
            isShow = false;
        }
    }

    public boolean isShowing() {
        return (mLoadingDialog != null && mLoadingDialog.isShowing())||isShow;
    }
}
