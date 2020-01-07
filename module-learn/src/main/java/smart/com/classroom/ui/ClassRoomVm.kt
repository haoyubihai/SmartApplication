package smart.com.classroom.ui

import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import smart.com.classroom.homework.ClassRoomData
import smart.com.classroom.homework.HomeWork
import java.util.concurrent.TimeUnit

class ClassRoomVm {

    private val classRoomRepository: ClassRoomRepository = ClassRoomRepository()
    lateinit var classRoomData: ClassRoomData
    //展示题目的id
    val showHomeWork = MutableLiveData<HomeWork>()
    var showTimeStaps = mutableListOf<Int>()

    init {
        getClassRoomData()
    }

    private fun getClassRoomData() {
        classRoomData = classRoomRepository.getClassRoomData()
        classRoomRepository.getTimeStap().map {
            showTimeStaps.add(it)
        }
    }


    fun showHomeworkView(time: Int) {
        val homeWork = classRoomRepository.findShowHomeHork(time)
        homeWork?.let {
            showHomeWork.value = it
        }
    }

    //    模拟视频推流
    fun play() {
      val sub =  Observable.interval(0, 1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (showTimeStaps.isNotEmpty()){
                    val time = showTimeStaps[0]
                    if (it.toInt()==time){
                        showHomeworkView(time)
                        showTimeStaps.removeAt(0)
                    }
                }

            }

    }
}