package ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jrh.library.common.utils.ToastUtil
import jrh.library.common.widgets.ProgressLoadingDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import views.IBaseView

@kotlinx.coroutines.ExperimentalCoroutinesApi
abstract class KBaseFragment : Fragment(), IBaseView, KodeinAware, CoroutineScope by MainScope() {

    override val kodein: Kodein by kodein()
    open var rootView: View? = null

    abstract val layoutId: Int


    private lateinit  var  loadingDialog: ProgressLoadingDialog


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(layoutId, container, false)
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDialog = ProgressLoadingDialog(activity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rootView = null
    }
    @kotlinx.coroutines.ExperimentalCoroutinesApi
    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    override fun showDialog(msg: String?) {
        if (!loadingDialog.isShowing)
            loadingDialog.show(msg)
    }

    override fun showDialog() {
        showDialog("")
    }

    override fun toast(msg: String?) {
        msg?.let { ToastUtil.showMessage(msg) }
    }

    override fun hideDialog() {
        if (loadingDialog.isShowing)
            loadingDialog.hide()
    }


}
