package smart.com

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.multidex.MultiDex
import com.jeremyliao.liveeventbus.LiveEventBus
import com.orhanobut.hawk.Hawk
import jrh.library.common.http.interceptor.NetworkInfoInterceptor
import jrh.library.common.http.request.Requester
import okhttp3.OkHttpClient
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import smart.com.classroom.vm.classRoomModule
import smart.com.http.DataError
import smart.com.http.HttpUrls
import smart.com.http.ParamsInterceptor
import smart.com.login.loginModule
import ui.KBaseApplication
import java.util.concurrent.TimeUnit

/**
 * desc:
 * Created by jiarh on 2019-10-10 14:04.
 */
class App : KBaseApplication() {
    val okHttpClient: OkHttpClient
        get() = builder.build()

    lateinit var builder: OkHttpClient.Builder

    companion object{
        const val CHANNEL = "com.aly.phone"
    }

    override fun onCreate() {
        super.onCreate()
        initCache()
        initCrash()
        initBus()
        initHttpConfig()
        initModules()
        initUpdate()
    }



    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(CHANNEL, "Upload Service", NotificationManager.IMPORTANCE_LOW)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun initUpdate() {

//        var major = 0
//        var min = 0
//        AppUtil.getVersionName().split(".")?.let {
//            major = it[0].toInt()
//            min = it[1].toInt()
//        }
//        XUpdate.get()
//            .debug(BuildConfig.DEBUG)
//            .isWifiOnly(false)
//            .isGet(false)
//            .isAutoMode(false)
//            .param("type",2)
//            .param("major",major)
//            .param("minor",min)
//            .setOnUpdateFailureListener {
////                ToastUtil.showMessage("更新异常，稍后重试。")
//            }
//            .supportSilentInstall(true)
//            .setIUpdateHttpService(XHttpUpdateHttpService("http://web.alangyun.com")).init(this)
    }



    private fun initModules() {

        startKoin {

            loadKoinModules(
                listOf(
                    loginModule,classRoomModule
                )
            )
        }

    }

    private fun initCache() {
        Hawk.init(this).build()
    }

    private fun initBus() {
        LiveEventBus
            .config()
            .supportBroadcast(this)
            .lifecycleObserverAlwaysActive(true)
            .autoClear(false)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun initCrash() {


//        RxJavaPlugins.setErrorHandler { throwable ->
//            //处理异常订阅，未处理异常的错误。
//            if (throwable != null) {
//                Timber.e("RXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXJAVA$throwable")
//            }
//        }

        //保存错误日志
//        CrashHandler.getInstance().init(this)

//        CrashReport.initCrashReport(applicationContext, "90fc5a56e2", BuildConfig.DEBUG)
    }

    private fun initHttpConfig() {

        builder = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(ParamsInterceptor())
            .addInterceptor(NetworkInfoInterceptor())


        val config = Requester.Config()
        config.setBaseUrl(HttpUrls.BASE_URL)
        config.setHttpClient(okHttpClient)
        config.setResultSuccessCode(DataError.SUCCESS)
        Requester.init(config)


    }
}
