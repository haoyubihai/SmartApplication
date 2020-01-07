package jrh.library.common.base;

import android.os.Bundle;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * desc:
 *
 * @author jiarh
 * @date 2018/8/8 10:38
 */

public abstract class BaseActivity extends AppCompatActivity implements ViewTreeObserver.OnGlobalLayoutListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
           }




}
