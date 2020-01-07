package jrh.library.common.widgets.toolbar;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.appcompat.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import aly.library.common.base.R;

/**
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * | ivNavButton  Title           ivRightL,ivRightM,ivRightR,RightTitleButton |
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * <p>
 * desc:
 * Created by jiarh on 2018/8/29 10:30.
 */
public class CommonToolBar extends Toolbar implements IToolBar, View.OnClickListener {

    public static final int TOOLBAR_NAV_BACK_ICON_ID = R.id.toolbar_nav_back_icon;
    public static final int TOOLBAR_NAV_TITLE_ID = R.id.toolbar_nav_title;
    public static final int TOOLBAR_RIGHT_LEFT_BTN_ID = R.id.toolbar_button_l;
    public static final int TOOLBAR_RIGHT_MID_BTN_ID = R.id.toolbar_button_m;
    public static final int TOOLBAR_RIGHT_RIGHT_BTN_ID = R.id.toolbar_button_r;
    public static final int TOOLBAR_RIGHT_TITLE_ID = R.id.toolbar_right_title;

    private View parentView;
    private ImageView ivNavButton, ivRightL, ivRightM, ivRightR;
    private TextView navTitle, rightTitleBtn;
    private List<Integer> imageViews = new ArrayList<>(3);
    private List<ImageView> imageDefaultViews = new ArrayList<>(3);
    private OnClickListener onDefaultClickListener;
    private Activity mAct;
    private View line;
    private boolean isBackIntercept;

    public void setBackIntercept(boolean backIntercept) {
        isBackIntercept = backIntercept;
    }

    public CommonToolBar(Context context) {
        this(context, null);
    }

    public CommonToolBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDefault();
    }

    private void initDefault() {
        inflate(getContext(), R.layout.common_toolbar, this);
        initView();
    }

    private void initView() {

        parentView = findViewById(R.id.parent_lay);
        ivNavButton = findViewById(R.id.iv_nav);
        ivRightL = findViewById(R.id.iv_right_l);
        ivRightM = findViewById(R.id.iv_right_m);
        ivRightR = findViewById(R.id.iv_right_r);
        navTitle = findViewById(R.id.tv_title);
        rightTitleBtn = findViewById(R.id.tv_btn_right);
        line = findViewById(R.id.line);

        resetIds();

        defaultClick();

        imageDefaultViews.clear();
        imageDefaultViews.add(ivRightR);
        imageDefaultViews.add(ivRightM);
        imageDefaultViews.add(ivRightL);
    }

    private void resetIds() {
        ivNavButton.setId(TOOLBAR_NAV_BACK_ICON_ID);
        navTitle.setId(TOOLBAR_NAV_TITLE_ID);
        ivRightL.setId(TOOLBAR_RIGHT_LEFT_BTN_ID);
        ivRightM.setId(TOOLBAR_RIGHT_MID_BTN_ID);
        ivRightR.setId(TOOLBAR_RIGHT_RIGHT_BTN_ID);
        rightTitleBtn.setId(TOOLBAR_RIGHT_TITLE_ID);
    }

    private void defaultClick() {
        ivNavButton.setOnClickListener(this);
        navTitle.setOnClickListener(this);
        ivRightL.setOnClickListener(this);
        ivRightM.setOnClickListener(this);
        ivRightR.setOnClickListener(this);
        rightTitleBtn.setOnClickListener(this);
    }


    @Override
    public CommonToolBar removeNavIcon() {
        ivNavButton.setVisibility(GONE);
        return this;
    }

    @Override
    public CommonToolBar setBackgroud(int bgColor) {
        parentView.setBackgroundColor(bgColor);
        return this.setNavIconBg(bgColor);
    }


    @Override
    public CommonToolBar setNavTitle(String title) {
        navTitle.setText(title);
        return this;

    }

    @Override
    public CommonToolBar setNavIconBg(int Color) {
        ivNavButton.setBackgroundColor(Color);
        return this;
    }

    @Override
    public CommonToolBar setNavTitle(@StringRes int titleId) {
        navTitle.setText(titleId);
        return this;

    }

    @Override
    public CommonToolBar setNavIcon(@DrawableRes int drawable) {
        ivNavButton.setImageResource(drawable);
        return this;

    }



    @Override
    public CommonToolBar setNavRightTitle(String rightTitle) {
        setNavRightTitle(rightTitle, R.color.white);
        return this;
    }


    @Override
    public CommonToolBar setNavRightTitle(String rightTitle, int color) {
        hideRightButtons();
        showRightTitle();
        rightTitleBtn.setText(rightTitle);
        rightTitleBtn.setTextColor(ContextCompat.getColor(getContext(), color));
        return this;
    }


    @Override
    public CommonToolBar addRightImgButton(@DrawableRes int view) {
        int size = imageViews.size();
        if (size < 3) {
            imageViews.add(view);
        } else {
            throw new IllegalArgumentException("最多支持3个");
        }
        addRightImgButtons(imageViews);
        return this;
    }

    @Override
    public CommonToolBar addRightImgButtons(List<Integer> views) {
        ArrayList<Integer> btnViews = new ArrayList<>(views);
        if (btnViews.size() > 3) {
            throw new IllegalArgumentException("最多支持3个");
        } else {
            hideRightTitle();
            for (int i = 0; i < btnViews.size(); i++) {
                imageDefaultViews.get(i).setVisibility(VISIBLE);
                imageDefaultViews.get(i).setImageResource(views.get(i));

            }
        }
        return this;
    }

    @Override
    public CommonToolBar hideRightButtons() {
        for (ImageView imageDefayltView : imageDefaultViews) {
            imageDefayltView.setVisibility(GONE);
        }
        return this;
    }

    @Override
    public CommonToolBar hideRightTitle() {
        rightTitleBtn.setVisibility(GONE);
        return this;
    }


    @Override
    public CommonToolBar showRightTitle() {
        rightTitleBtn.setVisibility(VISIBLE);
        return this;
    }

    @Override
    public CommonToolBar init(Activity activity, String navTitle, String rightTitle) {
        this.mAct = activity;
        setNavTitle(navTitle);
        setNavRightTitle(rightTitle);
        return this;
    }

    @Override
    public CommonToolBar init(String navTitle, String rightTitle) {
        setNavTitle(navTitle);
        setNavRightTitle(rightTitle);
        return this;
    }

    public void setOnDefaultClickListener(OnClickListener onDefaultClickListener) {
        this.onDefaultClickListener = onDefaultClickListener;
    }

    @Override
    public void onClick(View v) {
        if (!isBackIntercept&&v.getId() == TOOLBAR_NAV_BACK_ICON_ID && mAct != null) {

            mAct.onBackPressed();
        }
        if (onDefaultClickListener != null) {
            onDefaultClickListener.onClick(v);
        }
    }

    public TextView getNavTitle() {
        return navTitle;
    }

    public TextView getRightNavTitle() {
        return rightTitleBtn;
    }

    public ImageView getIvRightR() {
        return ivRightR;
    }

    public ImageView getIvRightM() {
        return ivRightM;
    }

    public View getLine() {
        return line;
    }
}
