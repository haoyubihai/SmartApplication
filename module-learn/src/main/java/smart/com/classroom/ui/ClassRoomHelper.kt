package smart.com.classroom.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.agora.rtc.Constants
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine
import jrh.library.common.app.AppConfig
import smart.com.classroom.APPID
import smart.com.classroom.CHANNEL
import smart.com.classroom.RTC_TOKEN
import smart.com.classroom.UID
import timber.log.Timber


class ClassRoomHelper(private val listener:RoomListener):ClassRoomListener {


    lateinit var mRtcEngine: RtcEngine
    val onFirstRemoteVideoFrame = MutableLiveData<Int>()
    val onUserOffline = MutableLiveData<Int>()
    val isVideoAndAudioPlaying = MutableLiveData<Boolean>(true)


    private val mRotcEventHandler = object : IRtcEngineEventHandler() {
        override fun onWarning(warn: Int) {
            Timber.e("ClassRoomHelper onWarning--$warn---currentThreadName=${Thread.currentThread().name}")
        }

        override fun onError(err: Int) {
            Timber.e("ClassRoomHelper onerror--$err---currentThreadName=${Thread.currentThread().name}")
        }

        // 本地用户成功加入频道时，会触发该回调。
        override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
            Timber.e("ClassRoomHelper onJoinChannelSuccess channel=$channel--uid=$uid--elapsed=$elapsed---currentThreadName=${Thread.currentThread().name}")

        }

        //SDK 接收到第一帧远端视频并成功解码时，会触发该回调。
        // 可以在该回调中调用 setupRemoteVideo 方法设置远端视图。
        override fun onFirstRemoteVideoFrame(uid: Int, width: Int, height: Int, elapsed: Int) {
            Timber.e("ClassRoomHelper onFirstRemoteVideoFrame uid=$uid--width=$width---currentThreadName=${Thread.currentThread().name}")

        }

        override fun onConnectionStateChanged(state: Int, reason: Int) {
            Timber.e("ClassRoomHelper onConnectionStateChanged state=$state--reason=$reason---currentThreadName=${Thread.currentThread().name}")
        }

        override fun onRemoteAudioStateChanged(uid: Int, state: Int, reason: Int, elapsed: Int) {
            Timber.e("ClassRoomHelper onRemoteAudioStateChanged state=$state--reason=$reason---currentThreadName=${Thread.currentThread().name}")
        }

        override fun onFirstRemoteVideoDecoded(uid: Int, width: Int, height: Int, elapsed: Int) {
            Timber.e("ClassRoomHelper onFirstRemoteVideoDecoded uid=$uid--width=$width---currentThreadName=${Thread.currentThread().name}")

            listener.onFirstRemoteVideoDecoded(uid, width, height, elapsed)
        }

        override fun onRemoteVideoStateChanged(uid: Int, state: Int, reason: Int, elapsed: Int) {
            Timber.e("ClassRoomHelper onRemoteVideoStateChanged state=$state--reason=$reason---currentThreadName=${Thread.currentThread().name}")
        }
        override fun onMediaEngineStartCallSuccess() {
        }

        override fun onUserOffline(uid: Int, reason: Int) {
            onUserOffline.value = reason
        }

    }

    fun initHelper() {
        try {
            mRtcEngine = RtcEngine.create(AppConfig.getContext(), APPID, mRotcEventHandler)
        } catch (e: Exception) {
            Timber.e(Log.getStackTraceString(e))
        }
        mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_COMMUNICATION)
        mRtcEngine.enableVideo()
        mRtcEngine.setClientRole(Constants.CLIENT_ROLE_AUDIENCE)
        mRtcEngine.joinChannel( RTC_TOKEN, CHANNEL, "", UID)


    }


    override fun leaveChannel() {
        mRtcEngine.leaveChannel()
        RtcEngine.destroy()
    }

    override fun openVideo() {
        mRtcEngine.muteAllRemoteVideoStreams(false)
    }

    override fun closeVideo() {
        mRtcEngine.muteAllRemoteVideoStreams(true)
    }

    override fun openAudio() {
        mRtcEngine.muteAllRemoteAudioStreams(false)
    }

    override fun closeAudio() {
        mRtcEngine.muteAllRemoteAudioStreams(true)
    }

    override fun openVideoAndAudio() {

        openVideo()
        openAudio()
    }

    override fun closeVideoAndAudio() {
        closeVideo()
        closeAudio()
    }

    override fun toggleVideoAndAudio() {
        isVideoAndAudioPlaying.value = isVideoAndAudioPlaying.value != true
        if (isVideoAndAudioPlaying.value==true){
            openVideoAndAudio()
        }else{
            closeVideoAndAudio()
        }
    }

}

interface RoomListener{
    fun onFirstRemoteVideoDecoded(uid: Int, width: Int, height: Int, elapsed: Int)
}

interface ClassRoomListener{
    fun leaveChannel()
    fun openVideo()
    fun closeVideo()
    fun openAudio()
    fun closeAudio()
    fun openVideoAndAudio()
    fun closeVideoAndAudio()
    fun toggleVideoAndAudio()
}