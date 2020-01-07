package jrh.library.common.contract.presenter;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import jrh.library.common.contract.BaseContract;


/**
 * desc:
 * Created by jiarh on 2018/8/8 15:32.
 */

public abstract class BasePresenter<V extends BaseContract.IView> extends DefaultLifecycleObserver implements BaseContract.IPresenter<V> {

    private LifecycleOwner lifecycleOwner;
    public V mView;

    public BasePresenter(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        addLifecycleObserver(lifecycleOwner);
    }

    /**
     * 注册生命周期的监听
     *
     * @param lifecycleOwner
     */
    private void addLifecycleObserver(LifecycleOwner lifecycleOwner) {
        if (lifecycleOwner != null) {
            lifecycleOwner.getLifecycle().addObserver(this);
        }
    }

    /**
     * 注销生命周期监听
     *
     */
    protected <T> AutoDisposeConverter<T> autoDispose(){

        return  AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner, Lifecycle.Event.ON_DESTROY));
    }

    @Override
    public void bindView(V view) {

        this.mView = view;
    }

    protected V getView(){
        return mView;
    }

    public LifecycleOwner getLifecycleOwner() {
        return lifecycleOwner;
    }

}
