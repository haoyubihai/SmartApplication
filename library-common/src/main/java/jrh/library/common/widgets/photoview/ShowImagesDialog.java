package jrh.library.common.widgets.photoview;


import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import aly.library.common.base.R;
import jrh.library.common.app.AppConfig;
import jrh.library.common.imageloader.utils.DisplayOptions;
import jrh.library.common.imageloader.utils.ImageLoader;
import jrh.library.common.widgets.photoview.bean.PictureEntity;


/**
 * Demo class
 *
 * @author ranxh
 * @e-mail : xianghui.ran@onesmart.org
 * @date 2019/5/617:18
 * @desc
 */

public class ShowImagesDialog extends Dialog {

    private View mView;
    private Context mContext;
    private ShowImagesViewPager mViewPager;
    private TextView mIndexText;
    private List<PictureEntity> mImgUrls;
    private int currentPosition;
    private List<String> mTitles;
    private List<View> mViews;
    private ShowImagesAdapter mAdapter;

    public ShowImagesDialog(@NonNull Context context, List<PictureEntity> imgUrls,int position) {
        super(context, R.style.transparentBgDialog);
        this.currentPosition = position;
        this.mContext = context;
        this.mImgUrls = imgUrls;
        initView();
        initData();
    }

    private void initView() {
        mView = View.inflate(mContext, R.layout.dialog_images_brower, null);
        mViewPager = mView.findViewById(R.id.vp_images);
        mIndexText = mView.findViewById(R.id.tv_image_index);
        mTitles = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mView);
        Window window = getWindow();
        window.setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        applyCompat(window);
    }
    private void applyCompat(Window window) {
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void initData() {

        for (int i = 0; i < mImgUrls.size(); i++) {
            final PhotoView photoView = new PhotoView(mContext);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            photoView.setLayoutParams(layoutParams);
            photoView.enable();
            if (mImgUrls.get(i).isLocal()){
                ImageLoader.getInstance().loadImage(photoView,"",new DisplayOptions.Builder(AppConfig.getContext())
                        .file(new File(mImgUrls.get(i).getPath()))
                        .build());
            }else{
                ImageLoader.getInstance().loadImage(photoView,mImgUrls.get(i).getPath());
            }
            photoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            mViews.add(photoView);
            mTitles.add(i + "");
        }

        mAdapter = new ShowImagesAdapter(mViews, mTitles);
        mViewPager.setAdapter(mAdapter);
        if (currentPosition<mViews.size()) {
            mViewPager.setCurrentItem(currentPosition);
            mIndexText.setText(currentPosition + 1 + "/" + mImgUrls.size());
        }else {
            mIndexText.setText(1 + "/" + mImgUrls.size());
        }
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mIndexText.setText(position + 1 + "/" + mImgUrls.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}

