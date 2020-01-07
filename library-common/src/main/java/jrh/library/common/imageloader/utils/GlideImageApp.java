package jrh.library.common.imageloader.utils;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import androidx.annotation.NonNull;

/**
 * desc:
 * Created by jiarh on 2019/2/27 15:19.
 */
@GlideModule
public class GlideImageApp extends AppGlideModule {

    public static final int M = 1024*1024;

    public static final int DEFAULT_CACHE_SIZE = 300*M;
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,"jrjyimgs", DEFAULT_CACHE_SIZE));
        RequestOptions options = new RequestOptions();
//        options.format(DecodeFormat.PREFER_ARGB_8888);
        options.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        builder.setDefaultRequestOptions(options);
    }
}
