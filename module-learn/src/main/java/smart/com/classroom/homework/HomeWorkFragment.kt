package smart.com.classroom.homework


import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.ValueCallback
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.jeremyliao.liveeventbus.LiveEventBus
import com.just.agentweb.WebChromeClient
import com.just.agentweb.WebViewClient
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
        homeWork = classRoomVm.classRoomRepository.findHomeWorkById(homeworkId!!)
        agentWeb.jsInterfaceHolder.addJavaObject("android",HomeworkWebHandler{
            Timber.e("返回的json==$it")
            val webCallRes = Gson().fromJson<WebCallRes>(it,WebCallRes::class.java)
            webCallRes?.let {
                when(it.type){
                    WEB_LOAD_BEGIN->{
                    }
                    WEB_RES_TIME_OVER->{
                        exit()
                    }
                    WEB_RES_CHOICE_RESULT->{

                    }
                }
            }
        })

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

    override fun getWebClient(): WebViewClient {
        return object :WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                initWebHomeworkData()
            }
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)


            }


        }
    }

    override fun getWebChromeClient(): WebChromeClient {
        return object:WebChromeClient(){

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)

            }



        }
    }

    private fun initWebHomeworkData() {
        homeWork?.let {
            Timber.e("找到试题--加载网页数据---${JSONHelper.toJSON(it)}")
            agentWeb.jsAccessEntrace.quickCallJs("callByAndroidMoreParams",  JSONHelper.toJSON(it))
        }
    }
}
