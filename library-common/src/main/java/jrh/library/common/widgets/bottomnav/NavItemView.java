package jrh.library.common.widgets.bottomnav;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import aly.library.common.base.R;

public class NavItemView extends LinearLayout {


    private TextView tvTitle;
    private TextView tvNum;
    private ImageView ivIcon;
    private Context mContext;

    public NavItemView(Context context) {
        this(context, null);
    }

    public NavItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public NavItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        inflate(context, R.layout.item_nav_view, this);

        mContext = context;
        tvTitle = findViewById(R.id.navTitle);
        tvNum = findViewById(R.id.navNum);
        ivIcon = findViewById(R.id.navIv);
    }

    int normalColor;
    int selectedColor;
    int normalIv;
    int pressedIv;
    public void init(
            @DrawableRes int ivResId,
            String text,
            int normalColor,
            int selectedColor
    ) {

        this.normalColor = normalColor;
        this.selectedColor = selectedColor;
        tvTitle.setText(text);
        tvTitle.setTextColor(normalColor);
        ivIcon.setImageDrawable(ContextCompat.getDrawable(mContext, ivResId));
    }

    public void hideNum(){
        tvNum.setVisibility(GONE);
    }
    public void showNum(int num){
        if (num<=0){
            hideNum();
        }
        tvNum.setVisibility(VISIBLE);
        tvNum.setText(num>99?"99+":num+"");
    }

    public void setSelected(boolean selected){
        tvTitle.setSelected(selected);
        ivIcon.setSelected(selected);
        tvTitle.setTextColor(selected?ContextCompat.getColor(mContext,selectedColor):ContextCompat.getColor(mContext,normalColor));
    }

}
