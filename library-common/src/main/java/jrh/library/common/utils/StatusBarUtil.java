package jrh.library.common.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;

public  class StatusBarUtil {

    public static int getStatusBarHeight(Activity activity) {
        View view=activity.getWindow().getDecorView();
        Rect frame=new Rect();
        view.getWindowVisibleDisplayFrame(frame);
        int result=frame.top;
        return result;
    }

    public static int getStatusBarHight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
