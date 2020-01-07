package smart.com.login

import jrh.library.common.http.request.Requester
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import viewmodel.KBaseViewModel

val loginModule = module {
    viewModel { LoginVm() }
}

class LoginVm :KBaseViewModel(){

    fun login(){
    }
}