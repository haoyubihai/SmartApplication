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
import kotlinx.android.synthetic.main.activity_class_room.*
import smart.com.R
import smart.com.classroom.homework.ChoiceFragment
import smart.com.classroom.homework.HomeWorkFragment
import smart.com.common.utils.PermissionUtils

const val CLOSE_CHOICE_FRAGMENT = "CLOSE_CHOICE_FRAGMENT"
class ClassRoomActivity : BaseActivity() {

    override val layoutId = R.layout.activity_class_room
    lateinit var choiceFragment: ChoiceFragment

    lateinit var classRoomVm: ClassRoomVm
    lateinit var classRoomHelper: ClassRoomHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        //全屏显示
        var lp = window.attributes
        adpat(lp)
        super.onCreate(savedInstanceState)
        checkUsePermission()

        initHelper()
        initData()
        initEventListener()
        initView()


    }

    private fun initData() {
        classRoomVm = ClassRoomVm()
        classRoomVm.play()
    }

    private fun initView() {
        ivBack.setOnClickListener { finish() }
        ivPlay.setOnClickListener { classRoomHelper.toggleVideoAndAudio() }

    }

    private fun initHelper() {
        classRoomHelper = ClassRoomHelper(object :RoomListener{
            override fun onFirstRemoteVideoDecoded(
                uid: Int,
                width: Int,
                height: Int,
                elapsed: Int
            ) {
                runOnUiThread {
                    val mRemoteView = RtcEngine.CreateRendererView(AppConfig.getContext())
                    classRoomHelper.mRtcEngine.setupRemoteVideo(VideoCanvas(mRemoteView, VideoCanvas.RENDER_MODE_HIDDEN, uid))
                    videoContainer.addView(mRemoteView)
                }
            }

        })
        classRoomHelper.initHelper()
    }

    private fun initEventListener() {
        classRoomHelper.isVideoAndAudioPlaying.observe(this,
            Observer<Boolean> {isPlaying->
                ivPlay.setImageResource(if (isPlaying) R.drawable.ic_stop else  R.drawable.ic_play)
                if (!isPlaying){

                }
            })

        classRoomVm.showHomeWork.observe(this, Observer {
            it?.let {
                toast("${it.title}")
                showHomeworkView()
            }
        })

        LiveEventBus.get(CLOSE_CHOICE_FRAGMENT).observe(this, Observer {
            removeFragment(homeworkContainer,choiceFragment)

        })
    }

    private fun showHomeworkView() {
        homeworkContainer.visibility = View.VISIBLE
        choiceFragment = ChoiceFragment()
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
        classRoomHelper.leaveChannel()
    }
}
