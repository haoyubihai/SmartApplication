package jrh.library.common.widgets.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * desc:
 * Created by jiarh on 2018/8/11 16:10.
 */

public class CommonWebView extends WebView {
    public CommonWebView(Context context) {
        this(context,null);
    }

    public CommonWebView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CommonWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
