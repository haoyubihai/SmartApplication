package jrh.library.common.utils;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

import jrh.library.common.app.AppConfig;
import timber.log.Timber;

/**
 * desc:
 * Created by jiarh on 18/1/31 14:46.
 */

public class AppUtil {


    /*
     * 获取当前程序的版本号
    */
    public static int getVersionCode() throws Exception {
        //获取packagemanager的实例
        PackageManager packageManager = AppConfig.getApp().getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(AppConfig.getApp().getPackageName(), 0);
        return packInfo.versionCode;
    }

    /*
 * 获取当前程序的版本名
 */
    public static String getVersionName() {
        //获取packagemanager的实例
        PackageManager packageManager = AppConfig.getApp().getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(AppConfig.getApp().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo.versionName;
    }

    public static String getPackageName() {
        //获取packagemanager的实例
        PackageManager packageManager = AppConfig.getApp().getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(AppConfig.getApp().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return packInfo.packageName;
    }

   static List<Activity> activities = new ArrayList<>();

    public static void registAct(Activity activity){
        if (activity!=null&&!activity.isFinishing()){
            activities.add(activity);
            Timber.i("acitivity 加入"+ activity.getClass().getSimpleName());
        }
    }

    public static void removeActs(){
        if (ListUtil.isNotEmpty(activities)){
            for (Activity activity : activities) {
                if (!activity.isFinishing()){
                    activity.finish();
                }
            }
        }
    }

    public static void restartApplication(Context context ,  Class<?> cls) {
        Intent mStartActivity = new Intent(context, cls);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId,mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 50, mPendingIntent);
        removeActs();
        System.exit(0);
    }

    public static void remove(Activity activity){
        if (activities.contains(activity)){
            int i = activities.indexOf(activity);
            activities.remove(i);
            Timber.i("acitivity 移除"+ activity.getClass().getSimpleName());

        }
    }

}
