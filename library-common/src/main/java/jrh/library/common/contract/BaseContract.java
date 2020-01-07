package jrh.library.common.contract;

/**
 * desc:
 * Created by jiarh on 2018/8/8 10:46.
 */

public interface BaseContract {

    interface IPresenter<V extends IView> {

        void bindView(V view);
    }

    interface IView {

        void toast(String msg);
        void showDialog(String msg);
        void showDialog();
        void hideDialog();
    }
}
