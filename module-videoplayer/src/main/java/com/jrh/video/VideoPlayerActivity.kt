package com.jrh.video

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.shuyu.gsyvideoplayer.GSYVideoADManager
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import kotlinx.android.synthetic.main.activity_video_player.*
import ui.KBaseActivity

open class VideoPlayerActivity : KBaseActivity() {
    override val layoutId: Int = R.layout.activity_video_player
    lateinit var videoNPlayer :StandardGSYVideoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPlayer()
    }

    private fun initPlayer() {
        videoNPlayer = getVideoPlayer()
        val url = getVideoUrl()
        val thumbImg = ImageView(this@VideoPlayerActivity).apply {
            scaleType = ImageView.ScaleType.CENTER_CROP
            setImageResource(R.mipmap.xxx1)
        }
        videoNPlayer.apply {
            setUp(url, true, getVideoTitle())
            thumbImageView = thumbImg
            titleTextView.visibility = View.VISIBLE
            backButton.visibility = View.VISIBLE
            setIsTouchWiget(true)
            backButton.setOnClickListener { onBackPressed() }
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        }.startPlayLogic()
    }

    private fun getVideoPlayer(): StandardGSYVideoPlayer  = videoPlayer

    override fun onPause() {
        super.onPause()
        videoPlayer.onVideoPause()
    }

    override fun onResume() {
        super.onResume()
        videoPlayer.onVideoResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoADManager.releaseAllVideos()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        videoPlayer.setVideoAllCallBack(null)
    }
    open fun getVideoUrl(): String = "https://jrvideo.oss-cn-zhangjiakou.aliyuncs.com/shouye.mp4"
    open fun getVideoTitle():String= "测试视频"

}
