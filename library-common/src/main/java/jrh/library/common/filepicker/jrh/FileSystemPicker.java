package jrh.library.common.filepicker.jrh;

import android.app.Activity;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.lang.ref.WeakReference;

/**
 * desc:
 * Created by jiarh on 2019-11-24 13:28.
 */
public class FileSystemPicker {


    private final WeakReference<Activity> mContext;
    private final WeakReference<Fragment> mFragment;

    private FileSystemPicker(Activity activity) {
        this(activity,null);
    }

    private FileSystemPicker(Fragment fragment){
        this(fragment.getActivity(),fragment);
    }

    private FileSystemPicker(Activity mContext, Fragment mFragment) {
        this.mContext = new WeakReference<>(mContext);
        this.mFragment = new WeakReference<>(mFragment);
    }

    public static FileSystemPicker from(Activity activity){
        return new FileSystemPicker(activity);
    }

    public static FileSystemPicker from(Fragment fragment){
        return new FileSystemPicker(fragment);
    }


    public void chooseFile(int requestCode){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        getActivity().startActivityForResult(intent,requestCode);
    }


    public Activity getActivity() {
        return mContext.get();
    }

    public Fragment getFragment() {
        return mFragment != null ? mFragment.get() : null;
    }
}
