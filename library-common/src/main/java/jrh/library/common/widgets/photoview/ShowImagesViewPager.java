package jrh.library.common.widgets.photoview;


import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Demo class
 *
 * @author ranxh
 * @e-mail : xianghui.ran@onesmart.org
 * @date 2019/5/617:19
 * @desc
 */
public class ShowImagesViewPager extends ViewPager {
    public ShowImagesViewPager(Context context) {
        this(context,null);
    }

    public ShowImagesViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            //uncomment if you really want to see these errors
            //e.printStackTrace();
            return false;
        }
    }
}
