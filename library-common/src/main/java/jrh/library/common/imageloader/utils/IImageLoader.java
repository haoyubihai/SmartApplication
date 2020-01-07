package jrh.library.common.imageloader.utils;

import android.widget.ImageView;

/**
 * desc:
 * Created by jiarh on 2019/2/27 15:12.
 */
public interface IImageLoader {
    void loadImage(ImageView iv,String imgUrl);
    void loadImage(ImageView iv,String imgUrl,DisplayOptions options);
}
