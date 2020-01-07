package utils

import jrh.library.common.utils.FileMimeMap
import jrh.library.common.utils.FileOpenUtils
import java.io.File


class FileTypeUtils {

    companion object {

        fun isAudio(file: File?): Boolean {
            file?.let {
                return isAudio(FileOpenUtils.getFileTypeName(file))
            }

            return false
        }

        fun isVideo(file: File?): Boolean {
            file?.let {
                return isVideo(FileOpenUtils.getFileTypeName(file))
            }
            return false
        }

        fun isImage(file: File?): Boolean {
            file?.let { return isImage(FileOpenUtils.getFileTypeName(file)) }
            return false
        }


        fun isAudio(name: String) = FileMimeMap.getMimeMapAll().filter {
            it.value.startsWith("audio")
        }.containsKey(name)

        fun isVideo(name: String) = FileMimeMap.getMimeMapAll().filter {
            it.value.startsWith("video")
        }.containsKey(name)

        fun isImage(name: String) = FileMimeMap.getMimeMapAll().filter {
            it.value.startsWith("image")
        }.containsKey(name)
    }
}