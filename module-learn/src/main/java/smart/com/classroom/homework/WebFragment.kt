package smart.com.classroom.homework


import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.aly.phone.base.ui.BaseFragment
import com.just.agentweb.*
import kotlinx.android.synthetic.main.fragment_choice_web.*
import smart.com.R

/**
 * A simple [Fragment] subclass.
 */


open class WebFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_choice_web

    lateinit var agentWeb: AgentWeb

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        agentWeb = AgentWeb.with(this) //
            .setAgentWebParent(
                getViewContainer(),
                -1,
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            ) //传入AgentWeb的父控件。
            .useDefaultIndicator(-1, 2) //设置进度条颜色与高度，-1为默认值，高度为2，单位为dp。
//            .setAgentWebWebSettings(getSettings()) //设置 IAgentWebSettings。
//            .setWebViewClient(mWebViewClient) //WebViewClient ， 与 WebView 使用一致 ，但是请勿获取WebView调用setWebViewClient(xx)方法了,会覆盖AgentWeb DefaultWebClient,同时相应的中间件也会失效。
//            .setWebChromeClient(CommonWebChromeClient()) //WebChromeClient
//            .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK) //严格模式 Android 4.2.2 以下会放弃注入对象 ，使用AgentWebView没影响。
//            .setAgentWebUIController(UIController()) //自定义UI  AgentWeb3.0.0 加入。
            .setMainFrameErrorView(
                R.layout.agentweb_error_page,
                -1
            ) //参数1是错误显示的布局，参数2点击刷新控件ID -1表示点击整个布局都刷新， AgentWeb 3.0.0 加入。
//            .useMiddlewareWebChrome(getMiddlewareWebChrome()) //设置WebChromeClient中间件，支持多个WebChromeClient，AgentWeb 3.0.0 加入。
//            .additionalHttpHeader(getUrl(), "cookie", "41bc7ddf04a26b91803f6b11817a5a1c")
//            .useMiddlewareWebClient(getMiddlewareWebClient()) //设置WebViewClient中间件，支持多个WebViewClient， AgentWeb 3.0.0 加入。
//            .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK) //打开其他页面时，弹窗质询用户前往其他应用 AgentWeb 3.0.0 加入。
//            .interceptUnkownUrl() //拦截找不到相关页面的Url AgentWeb 3.0.0 加入。
            .createAgentWeb() //创建AgentWeb。
            .ready() //设置 WebSettings。
            .go(getUrl()) //WebView载入该url地址的页面并显示。

// AgentWeb 没有把WebView的功能全面覆盖 ，所以某些设置 AgentWeb 没有提供 ， 请从WebView方面入手设置。
        // AgentWeb 没有把WebView的功能全面覆盖 ，所以某些设置 AgentWeb 没有提供 ， 请从WebView方面入手设置。
        agentWeb.webCreator.webView.overScrollMode = WebView.OVER_SCROLL_NEVER
    }

    open fun getViewContainer(): ViewGroup {

        return choiceWebContainer
    }

    open fun getUrl(): String? {
        return null
    }

    open fun getSettings(): IAgentWebSettings<WebSettings>? {
        return null
    }

    open fun getMiddlewareWebChrome() = object : MiddlewareWebChromeBase(){}

    private val mWebViewClient = object : WebViewClient(){}

}

class CommonWebChromeClient : WebChromeClient()

class UIController : AgentWebUIControllerImplBase()
