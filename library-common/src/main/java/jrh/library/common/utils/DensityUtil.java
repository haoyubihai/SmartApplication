package jrh.library.common.utils;

import android.content.Context;
import android.content.res.Resources;

public class DensityUtil {
	public static final float DENSITY, SCALED_DENSITY,SCREEN_WIDTH,SCREEN_HEIGHT;

	static {
		DENSITY = Resources.getSystem().getDisplayMetrics().density;
		SCALED_DENSITY = Resources.getSystem().getDisplayMetrics().scaledDensity;
		SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
		SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
	}

	/**
	 * dp转px
	 */
	public static int dp2px(float dpValue) {
		return (int) (dpValue * DENSITY + 0.5f);
	}

	/**
	 * px转dp
	 */
	public static int px2dp(float pxValue) {
		return (int) (pxValue / DENSITY + 0.5f);
	}

	/**
	 * px转sp
	 */
	public static int px2sp(float pxValue) {
		return (int) (pxValue / SCALED_DENSITY + 0.5f);
	}

	/**
	 * sp转px
	 */
	public static int sp2px(float spValue) {
		return (int) (spValue * SCALED_DENSITY + 0.5f);
	}

	public static int dip2px(Context context, float dpValue) {
		return (int) (dpValue * SCALED_DENSITY + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		return (int) (pxValue / SCALED_DENSITY + 0.5f);
	}
}
