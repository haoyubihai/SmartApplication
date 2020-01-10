package com.jrh.video.wigets;

import android.content.Context;
import android.util.AttributeSet;

import com.jrh.video.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

public class SmartVideoPlayer extends StandardGSYVideoPlayer {

    boolean isTouchProgressUse = false;

    public SmartVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public SmartVideoPlayer(Context context) {
        super(context);
    }

    public SmartVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return R.layout.video_player_view;
    }

    public void setTouchProgressUse(boolean touchProgressUse) {
        isTouchProgressUse = touchProgressUse;
    }

//    @Override
//    protected void showProgressDialog(float deltaX, String seekTime, int seekTimePosition, String totalTime, int totalTimeDuration) {
//        if (isTouchProgressUse) {
//            super.showProgressDialog(deltaX, seekTime, seekTimePosition, totalTime, totalTimeDuration);
//        }
//
//    }
//
//    @Override
//    protected void dismissProgressDialog() {
//        if (isTouchProgressUse) {
//            super.dismissProgressDialog();
//        }
//    }

    @Override
    protected void touchSurfaceMoveFullLogic(float absDeltaX, float absDeltaY) {
        super.touchSurfaceMoveFullLogic(absDeltaX, absDeltaY);
        if (!isTouchProgressUse) {
            mChangePosition = false;
        }
    }

}
