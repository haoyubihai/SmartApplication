package com.aly.phone.base.ui

import pub.devrel.easypermissions.EasyPermissions
import ui.KBaseFragment



/**
 * desc:
 * Created by jiarh on 2019-10-10 17:29.
 */
 abstract class BaseFragment:KBaseFragment(),EasyPermissions.PermissionCallbacks{

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }




}