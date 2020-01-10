package com.jrh.video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import kotlinx.android.synthetic.main.activity_video_player.*

open class VideoPlayerActivity2 : GSYBaseActivityDetail<StandardGSYVideoPlayer>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        videoPlayer.titleTextView.visibility = View.GONE
        videoPlayer.backButton.visibility = View.GONE
        initVideoBuilderMode()
        videoPlayer.setGSYVideoProgressListener { progress, secProgress, currentPosition, duration ->

        }
    }

    override fun clickForFullScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDetailOrientationRotateAuto() = false

    override fun getGSYVideoPlayer(): StandardGSYVideoPlayer {
        return  videoPlayer
    }

    override fun getGSYVideoOptionBuilder(): GSYVideoOptionBuilder {

        val url = getVideoUrl()
        val thumbImg = ImageView(this).apply {
            scaleType = ImageView.ScaleType.CENTER_CROP
            setImageResource(R.mipmap.xxx1)
        }

        return GSYVideoOptionBuilder().setThumbImageView(thumbImg)
            .setUrl(getVideoUrl())
            .setCacheWithPlay(true)
            .setVideoTitle(getVideoTitle())
            .setIsTouchWiget(false)
            .setRotateViewAuto(false)
            .setLockLand(false)
            .setShowFullAnimation(false)
            .setNeedLockFull(true)
            .setSeekRatio(1f)


    }
    open fun getVideoTitle():String= "测试视频"
    private fun getVideoUrl() =  "https://jrvideo.oss-cn-zhangjiakou.aliyuncs.com/shouye.mp4"
}
