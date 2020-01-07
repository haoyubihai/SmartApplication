package views


/**
 * desc:
 * Created by jiarh on 2019-06-19 19:13.
 */
interface IBaseView {
    fun showDialog()
    fun showDialog(msg:String?)
    fun hideDialog()
    fun toast(msg:String?)
}