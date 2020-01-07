package jrh.library.common.imageloader.utils;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jrh.library.common.app.AppConfig;
import jrh.library.common.utils.DensityUtil;
import jrh.library.common.utils.FileUtils;
import jrh.library.common.utils.StringUtil;

/**
 * desc:
 * Created by jiarh on 2019/2/27 15:15.
 */
public class GlideImageLoader implements IImageLoader {


    public static volatile GlideImageLoader instance = null;

    private GlideImageLoader() {
    }

    public static GlideImageLoader getInstance() {
        if (instance == null) {
            synchronized (GlideImageLoader.class) {
                if (instance == null) {
                    instance = new GlideImageLoader();
                }
            }
        }
        return instance;
    }


    @Override
    public void loadImage(ImageView iv, String imgUrl) {
        loadImage(iv, imgUrl, new DisplayOptions.Builder(AppConfig.getContext()).build());
    }

    @Override
    public void loadImage(ImageView iv, String imgUrl, final DisplayOptions options) {

        if (options == null) {
            return;
        }
        RequestOptions requestOptions = new RequestOptions()
                .priority(Priority.HIGH)
                .error(options.getError())
                .placeholder(options.getPlaceholder());
        if (options.getConnerRadius() > 0) {
            requestOptions.transform(new RoundedCornersTransformation(DensityUtil.dp2px(options.getConnerRadius()), 0));
        }
        if (options.isCircle()) {
            requestOptions.circleCrop();
        }
        if (options.getBlurOption() != null) {
            requestOptions.transform(new BlurTransformation(options.getBlurOption().getBlurDegree(), options.getBlurOption().getBlurMultiple()));
        }
        if (options.getWidth() > 0 && options.getHight() > 0) {
            requestOptions.override(options.getWidth(), options.getHight());

        }

        if (options.getScaleType() != null) {
            switch (options.getScaleType()) {
                case CENTER_CROP:
                    requestOptions.centerCrop();
                    break;
                case FIT_CENTER:
                    requestOptions.fitCenter();
                    break;
                case CENTER_INSIDE:
                    requestOptions.centerInside();
                    break;
            }
        }
        if (options.isSkipCache()) {
            requestOptions.skipMemoryCache(options.isSkipCache());
        }

        RequestManager requestManager = Glide.with(options.getContext());


        if (options.isGif()) {

            requestManager.asGif();
        }

        RequestBuilder<Drawable> requestBuilder = null;


        if (options.getDrawable() != 0) {

            requestBuilder = requestManager.load(options.getDrawable());
        }
        if (options.getFile() != null) {
            requestBuilder = requestManager.load(options.getFile());
        }
        if (StringUtil.isNotBlank(imgUrl)) {
            requestBuilder = requestManager.load(Uri.parse(imgUrl));
        }

        if (options.getOnImageLoadListener() != null) {
            requestManager.downloadOnly().load(imgUrl).into(new SimpleTarget<File>() {
                @Override
                public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                    options.getOnImageLoadListener().onDownloadReady(resource);
                    if (StringUtil.isNotBlank(options.getSavePath())) {
                        File file = new File(options.getSavePath());
                        if (!file.exists()) {
                            FileUtils.copy(resource, file);
                        }
                    }
                }
            });
        } else if (requestBuilder != null) {
            requestBuilder.apply(requestOptions).into(iv);
        } else {
            requestManager.load(imgUrl).apply(requestOptions).into(iv);
        }

    }


}
