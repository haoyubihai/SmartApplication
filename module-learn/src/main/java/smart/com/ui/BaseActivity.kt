package com.aly.phone.base.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.jeremyliao.liveeventbus.LiveEventBus
import com.xuexiang.xui.utils.KeyboardUtils
import com.xuexiang.xui.utils.StatusBarUtils
import pub.devrel.easypermissions.EasyPermissions
import smart.com.R
import ui.KBaseActivity
import ui.KBaseFragment


/**
 * desc:
 * Created by jiarh on 2019-10-10 14:54.
 */

val EVENT_SKIP_TO_LOGIN = "gotologin"
open abstract class BaseActivity : KBaseActivity(), EasyPermissions.PermissionCallbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.statusBarColor = statusBarColor()
        }
        StatusBarUtils.setStatusBarLightMode(this)
        super.onCreate(savedInstanceState)

        initEvent()
    }

    private fun initEvent() {

        LiveEventBus.get(EVENT_SKIP_TO_LOGIN,String::class.java)
            .observe(this, Observer {
            })
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }

    open fun setEditKeybord() {
        KeyboardUtils.setSoftInputAdjustResize(this)
    }
    open fun adpat(lp: WindowManager.LayoutParams) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            //下面图1
            //全屏显示
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.attributes = lp
        }
    }

    open fun statusBarColor() = ContextCompat.getColor(this, R.color.xui_config_color_background_phone)

    open fun loadFragment(containerId:Int,fragment: KBaseFragment)= supportFragmentManager.beginTransaction().add(containerId,fragment).commitNowAllowingStateLoss()
    open fun removeFragment(view:ViewGroup,fragment: KBaseFragment){
        supportFragmentManager.beginTransaction().remove(fragment)
        view.removeAllViews()
        view.visibility = View.GONE
    }
}