package smart.com.classroom.homework


import android.os.Bundle
import android.view.View
import android.webkit.ValueCallback
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.jeremyliao.liveeventbus.LiveEventBus
import kotlinx.android.synthetic.main.fragment_home_work.*
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel
import smart.com.R
import smart.com.classroom.homework.web.HomeworkWebHandler
import smart.com.classroom.ui.CLOSE_CHOICE_FRAGMENT
import smart.com.classroom.vm.ClassRoomVm
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class HomeWorkFragment : WebFragment() {
    override val layoutId: Int = R.layout.fragment_home_work
    private var homeworkId: Int? = null
    private var homeWork:HomeWork?=null

     val classRoomVm: ClassRoomVm by viewModel()

    companion object {
        @JvmStatic
        fun newInstance(homeWorkId: Int) =
            HomeWorkFragment().apply {
                arguments = Bundle().apply {
                   putInt ("hwID", homeWorkId)
                }
            }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            homeworkId = it.getInt("hwID")
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (homeworkId==null){
            toast("暂无试题信息")
            exit()
            return
        }
        agentWeb.jsInterfaceHolder.addJavaObject("android",HomeworkWebHandler{
            Timber.e("返回的json==$it")
            val webCallRes = Gson().fromJson<WebCallRes>(it,WebCallRes::class.java)

            webCallRes?.let {
                when(it.type){
                    WEB_RES_TIME_OVER->{
                        exit()
                    }
                    WEB_RES_CHOICE_RESULT->{

                    }
                }
            }
        })

        val homework = classRoomVm.classRoomRepository.findHomeWorkById(homeworkId!!)

        homework?.let {
            Timber.e("找到试题---${JSONHelper.toJSON(homework)}")
            agentWeb.jsAccessEntrace.quickCallJs("callByAndroidMoreParams",JSONHelper.toJSON(homework))
        }


    }

    private fun exit() {
        LiveEventBus.get(CLOSE_CHOICE_FRAGMENT).post(CLOSE_CHOICE_FRAGMENT)
    }

    private fun getJson(): String? {
        var result = ""
        try {
            val mJSONObject = JSONObject()
            mJSONObject.put("id", 1)
            mJSONObject.put("name", "Agentweb")
            mJSONObject.put("age", 18)
            result = mJSONObject.toString()
        } catch (e: Exception) {
        }
        return result
    }

    override fun getUrl(): String? {
        return "file:///android_asset/js/choice.html"
    }
    override fun getViewContainer() = webContainer
}
