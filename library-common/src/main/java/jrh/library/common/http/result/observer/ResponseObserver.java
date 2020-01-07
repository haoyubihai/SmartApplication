package jrh.library.common.http.result.observer;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * desc:
 * Created by jiarh on 2018/8/10 10:46.
 */

public class  ResponseObserver<T > implements Observer<T>,ResultEvent<T>{

    @Override
    public void onSubscribe(Disposable d) {

        onPreRequest();
    }

    @Override
    public void onNext(T t) {
        onEndRequest();
//        if (t==null){
//            onFailure("-1","请求网络失败，请稍候重试");
//            return;
//        }
//        if (t.isOk()){
            onSuccess(t);
//        }else {
//
//            onFailure(t.getErrcode(),t.getErrmsg());
//        }
    }

    @Override
    public void onError(Throwable e) {
        onEndRequest();
        Timber.e(e);
        onFailure("-1","请求网络失败，请稍候重试");
    }

    @Override
    public void onComplete() {
        onEndRequest();
    }


    @Override
    public void onPreRequest() {

    }

    @Override
    public void onEndRequest() {

    }

    @Override
    public void onSuccess(@NotNull T result) {

    }

    @Override
    public void onFailure(String code, String msg) {

    }
}
