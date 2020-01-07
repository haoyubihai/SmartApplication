package jrh.library.common.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.List;

import aly.library.common.base.BuildConfig;
import jrh.library.common.utils.ApplicationUtils;
import jrh.library.common.utils.ReflectUtils;
import timber.log.Timber;


/**
 * desc:
 * Created by jiarh on 2018/8/8 15:47.
 */

public class AppConfig {
    private static Context sContext;
    private static Application app;

    private AppConfig() {}

    public static void init(Application application) {
        app = application;
        sContext = app.getApplicationContext();
        initTimber();
        initImageSelector();

    }


    private static void initImageSelector() {

    }


    private static void initTimber() {
        if (BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static Context getContext() {
        return sContext;
    }

    public static Application getApp() {
        return app;
    }
    public static void appExit(){
        try {
            List<Activity> activities =  ApplicationUtils.getActivities();
            for (Activity activity:activities){
                activity.finish();
            }
        } catch (ReflectUtils.ReflectException e) {
            e.printStackTrace();
        }
    }
}
