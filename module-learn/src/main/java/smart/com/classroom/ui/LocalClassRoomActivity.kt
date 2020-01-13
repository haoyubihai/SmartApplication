package smart.com.classroom.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.aly.phone.base.ui.BaseActivity
import com.jeremyliao.liveeventbus.LiveEventBus
import com.shuyu.gsyvideoplayer.GSYVideoADManager
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import kotlinx.android.synthetic.main.activity_local_video_player.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import smart.com.R
import smart.com.classroom.homework.HomeWorkFragment
import smart.com.classroom.vm.ClassRoomVm
import timber.log.Timber
import kotlin.random.Random

open class LocalClassRoomActivity : BaseActivity() {
    override val layoutId: Int = R.layout.activity_local_video_player
    lateinit var videoNPlayer: StandardGSYVideoPlayer
    lateinit var choiceFragment: HomeWorkFragment
    private val classRoomVm: ClassRoomVm by viewModel()
    var isHomeworkShow = false
    var lastHwId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPlayer()
        initEventListener()
    }

    private fun initPlayer() {
        videoNPlayer = getVideoPlayer()
        val url = getVideoUrl()
        val thumbImg = ImageView(this@LocalClassRoomActivity).apply {
            scaleType = ImageView.ScaleType.CENTER_CROP
            setImageResource(R.mipmap.xxx1)
        }
        videoNPlayer.apply {
            setUp(url, true, getVideoTitle())
            thumbImageView = thumbImg
            titleTextView.visibility = View.GONE
            backButton.visibility = View.GONE
            setIsTouchWiget(true)
            backButton.setOnClickListener { onBackPressed() }
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        var time = 0
        videoNPlayer.setGSYVideoProgressListener { progress, secProgress, currentPosition, duration ->

            time++
            Timber.e("setGSYVideoProgressListener-视频定位到==old=${currentPosition}--currentPosition=${currentPosition/1000}--duration=$duration")
            classRoomVm.showHomeworkView(currentPosition/1000,currentPosition)
        }
    }

    private fun initEventListener() {


        classRoomVm.showHomeWork.observe(this, Observer {
            it?.let {
                //                toast("${it.title}")
//                classRoomVm.stop()
                Timber.e("showHomeworkView---it.id==${it}----lastHwId = $lastHwId")
                if (!isHomeworkShow&&it>=0&&it!=lastHwId){
                    lastHwId = it
                    isHomeworkShow = true
                    videoPlayer.onVideoPause()
                    showHomeworkView(it)

                }

            }
        })

        LiveEventBus.get(CLOSE_CHOICE_FRAGMENT).observe(this, Observer {
            if (isHomeworkShow){
                removeFragment(homeworkContainer, choiceFragment)
//            classRoomVm.play()
                Timber.e("############--视频定位到==原始=${videoNPlayer.currentPositionWhenPlaying}")
                val seekToPosition = videoNPlayer.currentPositionWhenPlaying+1500L

                Timber.e("############--视频定位到==修改后=${videoNPlayer.currentPositionWhenPlaying}")
//                videoPlayer.playPosition = seekToPosition.toInt()
                videoNPlayer.onVideoResume()
                videoNPlayer.seekTo(seekToPosition)
//                videoNPlayer.seekOnStart

                isHomeworkShow = false
            }

        })
    }


    private fun showHomeworkView(id:Int) {
        homeworkContainer.visibility = View.VISIBLE
        choiceFragment = HomeWorkFragment.newInstance(id)
        loadFragment(R.id.homeworkContainer, choiceFragment)
    }


    private fun getVideoPlayer(): StandardGSYVideoPlayer = videoPlayer

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

    open fun getVideoUrl(): String = "https://jrvideo.oss-cn-zhangjiakou.aliyuncs.com/%E5%AE%8C%E6%95%B4%E8%A7%86%E9%A2%91.mp4"
    open fun getVideoTitle(): String = "测试视频"

}
