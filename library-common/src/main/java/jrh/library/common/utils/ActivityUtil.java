package jrh.library.common.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

/**
 * desc:
 * Created by jiarh on 2018/8/8 15:55.
 */

public class ActivityUtil {

    public static boolean isActivityFinished(Activity mContext) {
        return mContext == null || mContext.isFinishing();
    }

    /**
     * 判断某activity是否处于栈顶
     *
     * @return true在栈顶 false不在栈顶
     */
    public static boolean isActivityTop(Class cls, Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String name = manager.getRunningTasks(1).get(0).topActivity.getClassName();
        return name.equals(cls.getName());

    }
}
