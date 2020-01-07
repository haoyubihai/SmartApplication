package jrh.library.common.imageloader.utils;

import android.content.Context;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import java.io.File;

/**
 * desc:
 * Created by jiarh on 2019/2/27 15:14.
 */
public class DisplayOptions {

    private int hight;
    private int width;
    private int placeholder;
    private int error;
    private Context context;
    private String url;
    private int connerRadius;
    private boolean circle;
    private boolean isGif;
    private ScaleType scaleType;
    private BlurOption blurOption;
    /**
     * 加载本地图片
     */
    private int drawable;
    /**
     * 路径加载图片
     */
    private File file;
    private OnImageLoadListener onImageLoadListener;
    /**
     * 保存图片的地址
     */
    private String savePath;
    /**
     * 是否使用缓存
     */
    private boolean skipCache;
    private DisplayOptions(){}

    private DisplayOptions(Builder builder) {
        hight = builder.hight;
        width = builder.width;
        placeholder = builder.placeholder;
        error = builder.error;
        context = builder.context;
        url = builder.url;
        connerRadius = builder.connerRadius;
        circle = builder.circle;
        isGif = builder.isGif;
        scaleType = builder.scaleType;
        blurOption = builder.blurOption;
        drawable = builder.drawable;
        file = builder.file;
        savePath = builder.savePath;
        onImageLoadListener = builder.onImageLoadListener;
    }


    public enum ScaleType {
        CENTER_CROP, FIT_CENTER, CENTER_INSIDE
    }

    /**
     * 高斯模糊
     */
    public static class BlurOption {
        /**
         * 模糊度
         */
        private int blurDegree = 15;
        /**
         * 模糊缩放倍数
         */
        private int blurMultiple = 1;

        public int getBlurDegree() {
            return blurDegree;
        }

        public void setBlurDegree(int blurDegree) {
            this.blurDegree = blurDegree;
        }

        public int getBlurMultiple() {
            return blurMultiple;
        }

        public void setBlurMultiple(int blurMultiple) {
            this.blurMultiple = blurMultiple;
        }
    }


    public interface OnImageLoadListener {

        void onDownloadReady(File file);
    }


    /**
     * {@code DisplayOptions} builder static inner class.
     */
    public static final class Builder {
        private int hight;
        private int width;
        private int placeholder;
        private int error;
        /**
         * 加载本地图片
         */
        private int drawable;
        /**
         * 路径加载图片
         */
        private File file;
        private Context context;
        private String url;
        private int connerRadius;
        private boolean circle;
        private boolean isGif;
        private ScaleType scaleType;
        private BlurOption blurOption;
        private boolean skipCache;
        private String savePath;
        private OnImageLoadListener onImageLoadListener;

        public Builder(Context context) {
            this.context = context;
        }


        public Builder file(File f) {
            file = f;
            return this;
        }

        public Builder drawable(@DrawableRes int drawableId) {
            drawable = drawableId;
            return this;

        }
        public Builder savePath(@NonNull String  imgPath) {
            savePath  = imgPath;
            return this;

        }

        public Builder listener(OnImageLoadListener val) {
            onImageLoadListener = val;
            return this;
        }

        /**
         * Sets the {@code hight} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code hight} to set
         * @return a reference to this Builder
         */
        public Builder hight(int val) {
            hight = val;
            return this;
        }

        /**
         * Sets the {@code width} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code width} to set
         * @return a reference to this Builder
         */
        public Builder width(int val) {
            width = val;
            return this;
        }

        /**
         * Sets the {@code placeholder} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code placeholder} to set
         * @return a reference to this Builder
         */
        public Builder placeholder(int val) {
            placeholder = val;
            return this;
        }

        /**
         * Sets the {@code error} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code error} to set
         * @return a reference to this Builder
         */
        public Builder error(int val) {
            error = val;
            return this;
        }


        /**
         * Sets the {@code url} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code url} to set
         * @return a reference to this Builder
         */
        public Builder url(String val) {
            url = val;
            return this;
        }

        /**
         * Sets the {@code connerRadius} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code connerRadius} to set
         * @return a reference to this Builder
         */
        public Builder connerRadius(int val) {
            connerRadius = val;
            return this;
        }

        /**
         * Sets the {@code circle} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code circle} to set
         * @return a reference to this Builder
         */
        public Builder circle(boolean val) {
            circle = val;
            return this;
        }

        /**
         * Sets the {@code isGif} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code isGif} to set
         * @return a reference to this Builder
         */
        public Builder isGif(boolean val) {
            isGif = val;
            return this;
        }

        /**
         * Sets the {@code scaleType} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code scaleType} to set
         * @return a reference to this Builder
         */
        public Builder scaleType(ScaleType val) {
            scaleType = val;
            return this;
        }

        /**
         * Sets the {@code blurOption} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code blurOption} to set
         * @return a reference to this Builder
         */
        public Builder blurOption(BlurOption val) {
            blurOption = val;
            return this;
        }

        public Builder skipCache(boolean skip) {
            skipCache = skip;
            return this;
        }

        /**
         * Returns a {@code DisplayOptions} built from the parameters previously set.
         *
         * @return a {@code DisplayOptions} built with parameters of this {@code DisplayOptions.Builder}
         */
        public DisplayOptions build() {
            return new DisplayOptions(this);
        }


    }

    public int getHight() {
        return hight;
    }

    public int getWidth() {
        return width;
    }

    public int getPlaceholder() {
        return placeholder;
    }

    public int getError() {
        return error;
    }

    public Context getContext() {
        return context;
    }

    public String getUrl() {
        return url;
    }

    public int getConnerRadius() {
        return connerRadius;
    }

    public boolean isCircle() {
        return circle;
    }

    public boolean isGif() {
        return isGif;
    }

    public ScaleType getScaleType() {
        return scaleType;
    }

    public BlurOption getBlurOption() {
        return blurOption;
    }

    public boolean isSkipCache() {
        return skipCache;
    }

    public int getDrawable() {
        return drawable;
    }

    public File getFile() {
        return file;
    }

    public String getSavePath() {
        return savePath;
    }

    public OnImageLoadListener getOnImageLoadListener() {
        return onImageLoadListener;
    }

}
