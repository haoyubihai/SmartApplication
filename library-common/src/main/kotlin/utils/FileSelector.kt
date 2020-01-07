package utils

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType

class FileSelector {
    companion object{

        fun audioSelect(context: FragmentActivity,requestCode:Int){
            PictureSelector.create(context)
                .openGallery(PictureMimeType.ofAudio())
                .maxSelectNum(1).isZoomAnim(false)
                .isSingleDirectReturn(true)
                .selectionMode(PictureConfig.SINGLE)
                .forResult(requestCode)
        }
    }
}