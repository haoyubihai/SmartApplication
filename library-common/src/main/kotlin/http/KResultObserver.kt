package http

import jrhlive.com.jbase.http.Resource
import jrhlive.com.jbase.http.Status
import views.IBaseView


/**
 * desc:
 * Created by jiarh on 2019-06-19 15:09.
 */
inline fun <T> kDialogObserver(resource: Resource<T>, view: IBaseView, observer: (resource: Resource<T>) -> Unit) {
    resource.let {
        when (resource.status) {
            Status.LOADING -> view.showDialog()
            else -> view.hideDialog()
        }

        if (resource.status == Status.SUCCESS || resource.status == Status.ERROR) {
            observer(resource)
        }
        if (resource.status == Status.ERROR) {
            view.toast(resource.message)
        }
    }
}

inline fun <T> kDialogNoToastObserver(resource: Resource<T>, view: IBaseView, observer: (resource: Resource<T>) -> Unit) {
    resource.let {
        when (resource.status) {
            Status.LOADING -> view.showDialog()
            else -> view.hideDialog()
        }
        if (resource.status == Status.SUCCESS || resource.status == Status.ERROR) {
            observer(resource)
        }
    }
}

inline fun <T> kObserver(resource: Resource<T>, view: IBaseView, observer: (resource: Resource<T>) -> Unit) {
    resource.let {
        if (resource.status == Status.SUCCESS || resource.status == Status.ERROR) {
            observer(resource)
        }
        if (resource.status == Status.ERROR) {
            view.toast(resource.message)
        }
    }
}