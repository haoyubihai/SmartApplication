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
            setIsTouchWiget(false)
            backButton.setOnClickListener { onBackPressed() }
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        val time = 0;
        videoNPlayer.setGSYVideoProgressListener { progress, secProgress, currentPosition, duration ->
            Timber.e("setGSYVideoProgressListener--progress=$progress--secProgress=$secProgress--currentPosition=$currentPosition--duration=$duration")
            classRoomVm.showHomeworkView(Math.floor(currentPosition / 1000.0).toInt())
        }
    }

    private fun initEventListener() {


        classRoomVm.showHomeWork.observe(this, Observer {
            it?.let {
                //                toast("${it.title}")
//                classRoomVm.stop()

                videoPlayer.onVideoPause()
                showHomeworkView()
            }
        })

        LiveEventBus.get(CLOSE_CHOICE_FRAGMENT).observe(this, Observer {
            removeFragment(homeworkContainer, choiceFragment)
//            classRoomVm.play()
            videoPlayer.onVideoResume()

        })
    }


    private fun showHomeworkView() {
        homeworkContainer.visibility = View.VISIBLE
        choiceFragment = HomeWorkFragment.newInstance(Random.nextInt(10))
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

    open fun getVideoUrl(): String = "https://jrvideo.oss-cn-zhangjiakou.aliyuncs.com/shouye.mp4"
    open fun getVideoTitle(): String = "测试视频"

}
