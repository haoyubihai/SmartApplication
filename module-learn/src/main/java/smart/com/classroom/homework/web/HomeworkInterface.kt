package smart.com.classroom.homework.web

import android.os.Handler
import android.os.Looper
import android.webkit.JavascriptInterface

class HomeworkWebHandler(var body:(data:String)->Unit) {

    val mHandler = Handler(Looper.getMainLooper())

    @JavascriptInterface
    fun callPhone(data:String) {
        mHandler.post {
            body(data)
        }
    }
}