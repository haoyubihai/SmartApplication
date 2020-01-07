package viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * desc:
 * Created by jiarh on 2019-06-18 11:54.
 */
open class KBaseViewModel:ViewModel(){

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        super.onCleared()
    }

}