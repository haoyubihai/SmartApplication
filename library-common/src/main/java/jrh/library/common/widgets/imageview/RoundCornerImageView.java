package jrh.library.common.widgets.imageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import aly.library.common.base.R;

/**
 * desc:
 * Created by jiarh on 2019/2/19 10:06.
 */
public class RoundCornerImageView extends androidx.appcompat.widget.AppCompatImageView {

    private float radius;
    private boolean topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius;
    private Paint mPaint;
    private Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    protected final RectF mRect = new RectF();
    public RoundCornerImageView(Context context) {
        this(context, null);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundCornerImageView);
        radius = array.getDimension(R.styleable.RoundCornerImageView_radius, 0);
        topLeftRadius = array.getBoolean(R.styleable.RoundCornerImageView_top_left_radius, true);
        topRightRadius = array.getBoolean(R.styleable.RoundCornerImageView_top_right_radius, true);
        bottomLeftRadius = array.getBoolean(R.styleable.RoundCornerImageView_bottom_left_radius, true);
        bottomRightRadius = array.getBoolean(R.styleable.RoundCornerImageView_bottom_right_radius, true);
        array.recycle();

        init();

    }

    private void init() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap bitmap = null;
        Drawable drawable = getDrawable();
        if (drawable==null){
            return;
        }
        int dWidth = drawable.getIntrinsicWidth();
        int dHeight = drawable.getIntrinsicHeight();
        if (drawable != null) {
            //创建一个bitmap
            bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            //创建画布
            Canvas drawCanvas = new Canvas(bitmap);
            float scale = 1.0f;
            //按照bitmap的宽高，以及view的宽高，计算缩放比例；因为设置的src宽高比例可能和imageview的宽高比例不同，这里我们不希望图片失真；
            // 如果图片的宽或者高与view的宽高不匹配，计算出需要缩放的比例；缩放后的图片的宽高，一定要大于我们view的宽高；所以我们这里取大值；
            scale = Math.max(getWidth() * 1.0f / dWidth, getHeight() * 1.0f / dHeight);
            //根据缩放比例，设置bounds，相当于缩放图片了
            drawable.setBounds(0, 0, (int) (scale * dWidth), (int) (scale * dHeight));
            drawable.draw(drawCanvas);

            mRect.set(0,0,(int) (scale * dWidth), (int) (scale * dHeight));

            Bitmap newBitmap = getBitmap();

            mPaint.reset();
            mPaint.setFilterBitmap(false);
            mPaint.setXfermode(xfermode);
            drawCanvas.drawBitmap(newBitmap, 0, 0, mPaint);
            mPaint.setXfermode(null);
            canvas.drawBitmap(bitmap, 0, 0, mPaint);


        }
    }

    private Bitmap getBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);

        RectF rectF = new RectF(0, 0, getWidth(), getHeight());
        canvas.drawRoundRect(rectF, radius, radius, mPaint);

        if (!topLeftRadius){

            canvas.drawRect(0,0,radius,radius,mPaint);
        }
        if (!topRightRadius){
            canvas.drawRect(mRect.right - radius, 0, mRect.right, radius, paint);
        }
        if (!bottomLeftRadius){
            canvas.drawRect(0,mRect.bottom-radius,radius,mRect.bottom,mPaint);
        }
        if (!bottomRightRadius){
            canvas.drawRect(mRect.right - radius, mRect.bottom - radius, mRect.right, mRect.bottom,
                    paint);
        }


        return bitmap;
    }
}
