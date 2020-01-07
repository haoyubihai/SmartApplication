package ui

import android.app.Application
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager
import com.xuexiang.xhttp2.XHttpSDK
import com.xuexiang.xui.BuildConfig
import com.xuexiang.xui.XUI
import jrh.library.common.app.AppConfig


/**
 * desc:
 * Created by jiarh on 2019-06-18 15:45.
 */
open class KBaseApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        AppConfig.init(this)
        XUI.init(this)
        XUI.debug(BuildConfig.DEBUG)
        XHttpSDK.init(this)
        XHttpSDK.debug("XHttp")
    }
}