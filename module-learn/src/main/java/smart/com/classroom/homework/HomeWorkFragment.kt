package smart.com.classroom.homework


import android.os.Bundle
import android.view.View
import android.webkit.ValueCallback
import androidx.fragment.app.Fragment
import com.jeremyliao.liveeventbus.LiveEventBus
import kotlinx.android.synthetic.main.fragment_home_work.*
import org.json.JSONObject
import smart.com.R
import smart.com.classroom.homework.web.HomeworkWebHandler
import smart.com.classroom.ui.CLOSE_CHOICE_FRAGMENT

/**
 * A simple [Fragment] subclass.
 */
class HomeWorkFragment : WebFragment() {
    override val layoutId: Int = R.layout.fragment_home_work
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        agentWeb.jsInterfaceHolder.addJavaObject("android",HomeworkWebHandler{
            tvContent.text = it
            LiveEventBus.get(CLOSE_CHOICE_FRAGMENT).post(CLOSE_CHOICE_FRAGMENT)
        })
        A.setOnClickListener { agentWeb.jsAccessEntrace.quickCallJs("sendHelloToAndroid") }
        B.setOnClickListener { agentWeb.jsAccessEntrace.quickCallJs("callByAndroidParam","Hello ! Agentweb") }
        C.setOnClickListener { agentWeb.jsAccessEntrace.quickCallJs("callByAndroidMoreParams",object :ValueCallback<String>{
            override fun onReceiveValue(value: String?) {

            }
        },getJson(),"aaa","bbb") }
        D.setOnClickListener { agentWeb.jsAccessEntrace.quickCallJs("callByAndroidInteraction","你好Js") }

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
        return "file:///android_asset/js_interaction/hello.html"
    }
    override fun getViewContainer() = webContainer
}
