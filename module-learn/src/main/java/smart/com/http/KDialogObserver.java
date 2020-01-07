package smart.com.http;


import jrh.library.common.contract.BaseContract;
import jrh.library.common.http.HttpConstant;
import jrh.library.common.http.result.observer.ResponseObserver;
import jrh.library.common.utils.StringUtil;
import jrh.library.common.utils.ToastUtil;

/**
 * desc:
 * Created by jiarh on 2018/8/10 11:28.
 */

public class KDialogObserver<T extends KResult> extends ResponseObserver<T> {

    private BaseContract.IView baseView;
    private String loadMsg;
    private boolean showToast;


    public KDialogObserver() {
        this(null, null, true);
    }

    public KDialogObserver(BaseContract.IView view) {
        this(view, null, true);
    }

    public KDialogObserver(BaseContract.IView view, String loadingMessage) {
        this(view, loadingMessage, true);
    }

    public KDialogObserver(BaseContract.IView view, String loadingMessage, boolean toastOnError) {

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
    public void onNext(T t) {
        onEndRequest();
        if (t == null) {
            onFailure("-2", "请求server异常");
            return;
        }
        if (t.getCode().equals("OK")) {
            onSuccess(t);
        } else {

            onFailure(t.getCode(), t.getDesc());
        }
    }

    @Override
    public void onEndRequest() {
        super.onEndRequest();
        if (baseView != null) {
            baseView.hideDialog();
        }
    }

    @Override
    public void onFailure(String code, String msg) {
        super.onFailure(code, msg);
        if (showToast && !HttpConstant.msgInterceptor(code)) {
            ToastUtil.showMessage(msg);
        }
    }
}
