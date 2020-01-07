package jrh.library.common.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

import aly.library.common.base.R;
import jrh.library.common.utils.TypedArrayUtil;


/**
 * 可以设置Drawable宽高的TextView
 *
 * @attr ref R.styleable#DrawableTextView_drawableWidth
 * @attr ref R.styleable#DrawableTextView_drawableHeight
 *
 */
public class DrawableTextView extends AppCompatTextView {
    private int mDrawableWidth, mDrawableHeight;

    public DrawableTextView(Context context) {
        super(context);
    }

    public DrawableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DrawableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (isInEditMode()) {
            return;
        }
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DrawableTextView);
        mDrawableWidth = TypedArrayUtil.getDimension(getContext(), typedArray, R.styleable.DrawableTextView_drawableWidth);
        mDrawableHeight = TypedArrayUtil.getDimension(getContext(), typedArray, R.styleable.DrawableTextView_drawableHeight);
        typedArray.recycle();

        Drawable[] drawables = getCompoundDrawables();
        for (Drawable drawable : drawables) {
            bindBounds(drawable);
        }
        setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    public void setDrawableLeft(int drawableRes) {
        setDrawable(0, drawableRes);
    }

    public void setDrawableLeft(Drawable drawable) {
        setDrawable(0, drawable);
    }

    public void setDrawableTop(int drawableRes) {
        setDrawable(1, drawableRes);
    }

    public void setDrawableTop(Drawable drawable) {
        setDrawable(1, drawable);
    }

    public void setDrawableRight(int drawableRes) {
        setDrawable(2, drawableRes);
    }

    public void setDrawableRight(Drawable drawable) {
        setDrawable(2, drawable);
    }

    public void setDrawableBottom(int drawableRes) {
        setDrawable(3, drawableRes);
    }

    public void setDrawableBottom(Drawable drawable) {
        setDrawable(3, drawable);
    }

    private void setDrawable(int index, int drawableRes) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), drawableRes);
        setDrawable(index, drawable);
    }

    private void setDrawable(int index, Drawable drawable) {
        bindBounds(drawable);
        Drawable[] drawables = getCompoundDrawables();
        setCompoundDrawables(index == 0 ? drawable : drawables[0],
                index == 1 ? drawable : drawables[1],
                index == 2 ? drawable : drawables[2],
                index == 3 ? drawable : drawables[3]);
    }

    private void bindBounds(Drawable drawable) {
        if (drawable != null) {
            drawable.setBounds(0, 0, mDrawableWidth > 0 ? mDrawableWidth : drawable.getIntrinsicWidth(),
                    mDrawableHeight > 0 ? mDrawableHeight : drawable.getIntrinsicHeight());
        }
    }
}
