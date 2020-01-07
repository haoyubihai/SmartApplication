package jrh.library.common.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import jrh.library.common.contract.BaseContract;
import jrh.library.common.utils.StringUtil;
import jrh.library.common.utils.ToastUtil;
import jrh.library.common.widgets.ProgressLoadingDialog;

/**
 * desc:
 *
 * Created by jiarh on 2018/8/8 10:39.
 */

public  abstract class BaseFragment extends BaseLazyFragment implements BaseContract.IView {

    ProgressLoadingDialog loadingDialog ;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadingDialog = new ProgressLoadingDialog(getActivity());

    }



    @Override
    public void toast(String msg) {
        if (StringUtil.isNotBlank(msg)){
            ToastUtil.showMessage(msg);
        }
    }

    @Override
    public void showDialog(String msg) {
        if (loadingDialog != null) {
            loadingDialog.show(msg);
        }
    }

    @Override
    public void showDialog() {
        if (loadingDialog != null) {
            loadingDialog.show();
        }
    }

    @Override
    public void hideDialog() {

        if (loadingDialog != null) {
            loadingDialog.hide();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
