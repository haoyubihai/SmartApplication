package smart.com.classroom.vm

import androidx.lifecycle.MutableLiveData
import cn.dreamtobe.kpswitch.IFSPanelConflictLayout
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import smart.com.classroom.CHANNEL
import smart.com.classroom.RTM_TOKEN
import smart.com.classroom.UID
import smart.com.classroom.homework.ClassRoomData
import smart.com.classroom.homework.HomeWork
import smart.com.classroom.util.RTCHelper
import smart.com.classroom.util.RTMHelper
import timber.log.Timber
import viewmodel.KBaseViewModel
import java.util.concurrent.TimeUnit

val classRoomModule = module {
    viewModel { ClassRoomVm() }
}


class ClassRoomVm : KBaseViewModel() {

    val classRoomRepository: ClassRoomRepository =
        ClassRoomRepository()
    lateinit var classRoomData: ClassRoomData
    //展示题目的id
    val showHomeWork = MutableLiveData<Int>()
    var showTimeStaps = mutableListOf<Int>()
    val progress = MutableLiveData<Int>()

    val rtmHelper = RTMHelper()
    val rtcHelper = RTCHelper()

    var nextShowTime: Int = -1

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
        nextShowTime = showTimeStaps[0]

    }

    fun getData() {
        runBlocking {

            val name = async { testGetName() }
            val title = async { testGetTitle() }



            Timber.e("getData---runBlocking = ${name.await() + title.await()}")
        }
        CoroutineScope(Dispatchers.IO).launch {
            Timber.e("getData---threadName111 = ${Thread.currentThread().name}")
            CoroutineScope(Dispatchers.Main).launch {
                Timber.e("getData---threadName222 = ${Thread.currentThread().name}")
            }
        }
        Timber.e("getData---11111outter--threadName = ${Thread.currentThread().name}")
    }

    fun testGetName(): String {
        Thread.sleep(2000)
        return "hello"
    }

    fun testGetTitle(): String {
        Thread.sleep(3000)
        return "kotlin"
    }

    fun isInShowTimes(time:Int):Int?{
        val inShow = showTimeStaps.contains(time)
        if (inShow){
            return classRoomRepository.findShowHomeworkId(time)
        }
        return null
    }


    fun showHomeworkView(time: Int,priPosition:Int?=null) {
//        Timber.e("showHomeworkView------time=$time")

//        if (nextShowTime in 1..time) {
            val homeWork = isInShowTimes(time)
            homeWork?.let {
                Timber.e("############--showHomeworkView---priPosition =$priPosition ----ShowTime=$time--")
                showHomeWork.value = it

//                showTimeStaps.removeAt(0)
//                nextShowTime = if (showTimeStaps.isNotEmpty()) {
//                    showTimeStaps[0]
//                } else {
//                    //没有时间段后 不用再判断
//                    -1
//                }
            }
//        }


    }

    lateinit var sub: Disposable
    var timeStemp = 0
    //    模拟视频推流
    fun play() {
        sub = Observable.interval(0, 1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                timeStemp++
                progress.value = timeStemp
                if (showTimeStaps.isNotEmpty()) {
                    val time = showTimeStaps[0]
                    if (timeStemp == time) {
                        showHomeworkView(time)
                        showTimeStaps.removeAt(0)
                    }
                }

            }
    }

    fun stop() {
        sub.dispose()
    }
}