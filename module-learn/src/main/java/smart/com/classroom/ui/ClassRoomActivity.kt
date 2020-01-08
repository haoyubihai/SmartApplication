package smart.com.classroom.ui

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.Observer
import com.aly.phone.base.ui.BaseActivity
import com.jeremyliao.liveeventbus.LiveEventBus
import io.agora.rtc.RtcEngine
import io.agora.rtc.video.VideoCanvas
import jrh.library.common.app.AppConfig
import jrh.library.common.utils.TimeUtils
import kotlinx.android.synthetic.main.activity_class_room.*
import smart.com.R
import smart.com.classroom.homework.*
import smart.com.classroom.util.RTCHelper
import smart.com.classroom.vm.ClassRoomVm
import smart.com.common.utils.PermissionUtils
import timber.log.Timber
import kotlin.random.Random

const val CLOSE_CHOICE_FRAGMENT = "CLOSE_CHOICE_FRAGMENT"
class ClassRoomActivity : BaseActivity() {

    override val layoutId = R.layout.activity_class_room
    lateinit var choiceFragment: HomeWorkFragment

    lateinit var classRoomVm: ClassRoomVm
    lateinit var rtcHelper: RTCHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        //全屏显示
        var lp = window.attributes
        adpat(lp)
        super.onCreate(savedInstanceState)
        checkUsePermission()

        initData()
        initEventListener()
        initView()

        JSONHelper.toJSON(WebCallRes(WEB_RES_CHOICE_RESULT, arrayListOf(KeyValue("A","handbag")))).let {
            Timber.e("返回结果===$it")
        }

    }

    private fun initData() {
        classRoomVm = ClassRoomVm()
        rtcHelper = classRoomVm.rtcHelper
//        classRoomVm.play()
    }

    private fun initView() {
        ivBack.setOnClickListener { finish() }
        ivPlay.setOnClickListener { rtcHelper.toggleVideoAndAudio() }

    }



    private fun addRemoteView(uid: Int) {
        val mRemoteView = RtcEngine.CreateRendererView(AppConfig.getContext())
        rtcHelper.mRtcEngine.setupRemoteVideo(
            VideoCanvas(
                mRemoteView,
                VideoCanvas.RENDER_MODE_HIDDEN,
                uid
            )
        )
        videoContainer.addView(mRemoteView)
    }

    private fun initEventListener() {
        rtcHelper.isVideoAndAudioPlaying.observe(this,
            Observer<Boolean> {isPlaying->
                ivPlay.setImageResource(if (isPlaying) R.drawable.ic_stop else  R.drawable.ic_play)
                if (!isPlaying){
                    showHomeworkView()
                }
            })

        rtcHelper.onFirstRemoteVideoDecoded.observe(this, Observer { uid->
            toast("onFirstRemoteVideoDecoded--")
            addRemoteView(uid)
        })

        classRoomVm.showHomeWork.observe(this, Observer {
            it?.let {
//                toast("${it.title}")
//                classRoomVm.stop()
//                showHomeworkView()
            }
        })
        classRoomVm.progress.observe(this, Observer {
            tvProgress.text = TimeUtils.formatDuration(it*1000)
        })

        LiveEventBus.get(CLOSE_CHOICE_FRAGMENT).observe(this, Observer {
            removeFragment(homeworkContainer,choiceFragment)
//            classRoomVm.play()

        })
    }

    private fun showHomeworkView() {
        homeworkContainer.visibility = View.VISIBLE
        choiceFragment = HomeWorkFragment.newInstance(Random.nextInt(10))
        loadFragment(R.id.homeworkContainer, choiceFragment)
    }

    private fun checkUsePermission() {
       val hasAudioCamera = PermissionUtils.hasAudioCamera(this)
        if (!hasAudioCamera){
            PermissionUtils.reqAudioCamera(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        rtcHelper.leaveChannel()
    }
}
