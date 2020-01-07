package jrh.library.common.imageloader.utils;

import android.widget.ImageView;

/**
 * desc:
 * Created by jiarh on 2019/2/27 17:11.
 */
public class ImageLoader implements IImageLoader {

    private static volatile ImageLoader instance = null;

    IImageLoader imageLoader;

    public static IImageLoader getInstance() {
        if (instance == null) {
            synchronized (IImageLoader.class) {
                if (instance == null) {
                    instance = new ImageLoader();
                }
            }

        }
        return instance;
    }

    private ImageLoader() {
        registImageLoader(GlideImageLoader.getInstance());
    }

    private void registImageLoader(IImageLoader instance) {
        imageLoader = instance;
    }

    @Override
    public void loadImage(ImageView iv, String imgUrl) {
        imageLoader.loadImage(iv, imgUrl);
    }

    @Override
    public void loadImage(ImageView iv, String imgUrl, DisplayOptions options) {
        imageLoader.loadImage(iv, imgUrl, options);
    }
}
