package smart.com.common.utils

import android.Manifest
import android.content.Context
import androidx.fragment.app.FragmentActivity
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest
import smart.com.common.PERMISSION_AUDIO
import smart.com.common.PERMISSION_AUDIO_CAMERA
import smart.com.common.PERMISSION_STORAGE

class PermissionUtils {

    companion object {

        fun hasStorage(context: Context) =
            EasyPermissions.hasPermissions(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )

        fun reqStorage(context: FragmentActivity) = EasyPermissions.requestPermissions(
            PermissionRequest.Builder(
                context, PERMISSION_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ).setRationale("为保证正常功能使用，请求读取存储权限").build()
        )

        fun hasAudio(context: Context) =
            EasyPermissions.hasPermissions(
                context,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_PHONE_STATE
            )


        fun reqAudio(context: FragmentActivity) = EasyPermissions.requestPermissions(
            PermissionRequest.Builder(
                context, PERMISSION_AUDIO,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_PHONE_STATE
            ).setRationale("为保证正常功能使用，请求权限").build()
        )

        fun hasAudioCamera(context: Context) =   EasyPermissions.hasPermissions(
            context,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        fun reqAudioCamera(context: FragmentActivity) = EasyPermissions.requestPermissions(
            PermissionRequest.Builder(
                context, PERMISSION_AUDIO_CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).setRationale("为保证正常功能使用，请求权限").build()
        )



    }
}