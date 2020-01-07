package jrh.library.common.http.result.observer;

import jrh.library.common.contract.BaseContract;
import jrh.library.common.http.HttpConstant;
import jrh.library.common.utils.StringUtil;
import jrh.library.common.utils.ToastUtil;

/**
 * desc:
 * Created by jiarh on 2018/8/10 11:28.
 */

public class DialogObserver<T> extends ResponseObserver<T> {

    private BaseContract.IView baseView;
    private String loadMsg;
    private boolean showToast;


    public DialogObserver() {
        this(null, null, true);
    }
    public DialogObserver(BaseContract.IView view) {
        this(view, null, true);
    }

    public DialogObserver(BaseContract.IView view, String loadingMessage) {
        this(view, loadingMessage, true);
    }

    public DialogObserver(BaseContract.IView view, String loadingMessage, boolean toastOnError) {

        this.baseView = view;
        this.loadMsg = loadingMessage;
        this.showToast = toastOnError;
    }

    @Override
    public void onPreRequest() {
        super.onPreRequest();
        if (baseView != null) {
            if (StringUtil.isBlank(loadMsg)) {
                baseView.showDialog();
            } else {
                baseView.showDialog(loadMsg);
            }
        }
    }

    @Override
    public void onEndRequest() {
        super.onEndRequest();
        if (baseView!=null){
            baseView.hideDialog();
        }
    }

    @Override
    public void onFailure(String code, String msg) {
        super.onFailure(code, msg);
        if (showToast&&!HttpConstant.msgInterceptor(code)){
            ToastUtil.showMessage(msg);
        }
    }
}
