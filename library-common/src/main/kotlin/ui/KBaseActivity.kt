package ui

import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import jrh.library.common.utils.ToastUtil
import jrh.library.common.widgets.ProgressLoadingDialog
import views.IBaseView
import java.util.concurrent.TimeUnit


/**
 * desc:
 * Created by jiarh on 2019-06-18 14:29.
 */

abstract class KBaseActivity : AppCompatActivity(), IBaseView,  ViewTreeObserver.OnGlobalLayoutListener {


    private lateinit var loadingDialog: ProgressLoadingDialog


    abstract val layoutId: Int

    private val compositeDisposable:CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        loadingDialog = ProgressLoadingDialog(this)


    }



    override fun onDestroy() {
        super.onDestroy()

    }

    override fun showDialog(msg: String?) {
        if (!loadingDialog.isShowing)
            loadingDialog.show(msg)
    }

    override fun hideDialog() {
        if (loadingDialog.isShowing)
            loadingDialog.hide()
    }

    override fun showDialog() {
        showDialog("")
    }

    override fun toast(msg: String?) {
        msg?.let { ToastUtil.showMessage(msg) }
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    override fun onGlobalLayout() {
    }

    protected fun Disposable.addToDisposables(){
        compositeDisposable.add(this)
    }

    /**
     *  用来替代 view.postDelay(Runnable())
     */
    protected fun postSafely(callBack:()->Unit,delay: Long=0,delayTimeUnit: TimeUnit=TimeUnit.MILLISECONDS){
        Completable.complete().subscribeOn(Schedulers.newThread())
                .delay(delay,delayTimeUnit)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(callBack)
                .subscribe()
                .addToDisposables()
    }
}