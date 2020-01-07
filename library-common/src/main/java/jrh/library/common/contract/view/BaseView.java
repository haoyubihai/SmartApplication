package jrh.library.common.contract.view;

import android.app.Activity;
import android.app.Fragment;

import jrh.library.common.contract.BaseContract;
import jrh.library.common.utils.ToastUtil;
import jrh.library.common.widgets.ProgressLoadingDialog;


/**
 * desc:
 * Created by jiarh on 2018/8/8 15:31.
 */

public class BaseView implements BaseContract.IView {

    private ProgressLoadingDialog progressLoadingDialog;
    private Activity mAct;

    public BaseView(Activity activity) {
        this.mAct =activity;
        initDialog();
    }

    public BaseView(Fragment fragment){
        if (fragment!=null){
            this.mAct = fragment.getActivity();
        }
        initDialog();
    }

    private void initDialog() {
        progressLoadingDialog = new ProgressLoadingDialog(mAct);
    }

    @Override
    public void toast(String msg) {
        ToastUtil.showMessage(msg);
    }

    @Override
    public void showDialog(String msg) {

        if (progressLoadingDialog != null) {
            progressLoadingDialog.show(msg);
        }
    }

    @Override
    public void showDialog() {
        if (progressLoadingDialog != null) {
            progressLoadingDialog.show();
        }

    }

    @Override
    public void hideDialog() {

        if (progressLoadingDialog != null) {
            progressLoadingDialog.hide();
        }
    }
}
