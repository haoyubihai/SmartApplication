package jrh.library.common.widgets.toolbar;

import android.app.Activity;

import java.util.List;

/**
 * desc:
 * Created by jiarh on 2018/8/29 10:38.
 */
public interface IToolBar {

    CommonToolBar removeNavIcon();

    CommonToolBar setBackgroud(int bgColor);

    CommonToolBar setNavTitle(String title);
    CommonToolBar setNavIconBg(int Color);

    CommonToolBar setNavTitle(int titleId);
    CommonToolBar setNavIcon(int drawable);
    CommonToolBar setNavRightTitle(String rightTitle);
    CommonToolBar setNavRightTitle(String rightTitle, int color);
    CommonToolBar addRightImgButton(int viewId);
    CommonToolBar addRightImgButtons(List<Integer> viewIds);

    CommonToolBar hideRightButtons();
    CommonToolBar hideRightTitle();

    CommonToolBar showRightTitle();

    /**
     * 传activity默认返回 finish
     * @param activity
     * @param navTitle
     * @param rightTitle
     */
    CommonToolBar init(Activity activity, String navTitle, String rightTitle);

    CommonToolBar init(String navTitle, String rightTitle);

}
