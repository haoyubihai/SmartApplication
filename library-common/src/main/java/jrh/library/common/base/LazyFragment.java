package jrh.library.common.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public abstract class LazyFragment extends Fragment {
    private boolean mVisibleToUser;
    private boolean mInitialized;
    private boolean mForceRequest;
    public View mRootView;
    public boolean isUseLazyFragment;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        if (isUseLazyFragment()){
//            if (mVisibleToUser || mForceRequest) {
//                mInitialized = true;
//                requestData();
//            }
//        }else {
//            requestData();
//        }
        requestData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        if (isUseLazyFragment()) {
//            this.mVisibleToUser = isVisibleToUser;
//            if (isAdded() && isVisibleToUser && !mInitialized) {
//                mInitialized = true;
//                requestData();
//            }
//        }

    }

    /**
     * 不要重写
     */
    private void requestData() {
        initView();
        initData();
    }

    //初始化控件
    protected abstract void initView();

    //初始化数据
    protected abstract void initData();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView==null){
            mRootView = getRootView(inflater,container);
        }
        return mRootView;
    }

    protected abstract View getRootView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);

    public void setForceRequest(boolean forceRequest) {
        this.mForceRequest = forceRequest;
    }

    protected boolean isInitialized() {
        return mInitialized;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null!=mRootView&&mRootView.getParent()!=null){
            ((ViewGroup)mRootView.getParent()).removeView(mRootView);
        }
        mInitialized = false;
        mVisibleToUser = false;
    }

    public boolean isUseLazyFragment() {
        return isUseLazyFragment;
    }
}
