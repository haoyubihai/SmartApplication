package smart.com.classroom.vm

import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import smart.com.classroom.CHANNEL
import smart.com.classroom.RTM_TOKEN
import smart.com.classroom.UID
import smart.com.classroom.homework.ClassRoomData
import smart.com.classroom.homework.HomeWork
import smart.com.classroom.util.RTCHelper
import smart.com.classroom.util.RTMHelper
import viewmodel.KBaseViewModel
import java.util.concurrent.TimeUnit

val classRoomModule = module {
    viewModel { ClassRoomVm() }
}


class ClassRoomVm : KBaseViewModel(){

    val classRoomRepository: ClassRoomRepository =
        ClassRoomRepository()
    lateinit var classRoomData: ClassRoomData
    //展示题目的id
    val showHomeWork = MutableLiveData<HomeWork>()
    var showTimeStaps = mutableListOf<Int>()
    val progress = MutableLiveData<Int>()

    val rtmHelper =RTMHelper()
    val rtcHelper = RTCHelper()

    init {
        getClassRoomData()
    }


    private fun getClassRoomData() {
        classRoomData = classRoomRepository.getClassRoomData()
        classRoomRepository.getTimeStap().map {
            showTimeStaps.add(it)
        }
        rtcHelper.initHelper()
        rtmHelper.initData(RTM_TOKEN, UID.toString(), CHANNEL)

    }


    fun showHomeworkView(time: Int) {
        val homeWork = classRoomRepository.findShowHomeWork(time)
        homeWork?.let {
            showHomeWork.value = it
        }
    }
    lateinit var sub:Disposable
    var  timeStemp =0
    //    模拟视频推流
    fun play() {
       sub=  Observable.interval(0, 1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                timeStemp++
                progress.value=timeStemp
                if (showTimeStaps.isNotEmpty()){
                    val time = showTimeStaps[0]
                    if (timeStemp==time){
                        showHomeworkView(time)
                        showTimeStaps.removeAt(0)
                    }
                }

            }
    }

    fun stop(){
        sub.dispose()
    }
}