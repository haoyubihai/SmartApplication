package jrh.library.common.base;

import android.app.Application;

import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;

import jrh.library.common.app.AppConfig;

/**
 * desc:
 * Created by jiarh on 2018/8/8 10:46.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppConfig.init(this);
        QMUISwipeBackActivityManager.init(this);
    }
}
