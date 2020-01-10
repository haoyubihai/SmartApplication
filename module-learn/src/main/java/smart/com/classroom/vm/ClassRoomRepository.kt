package smart.com.classroom.vm

import smart.com.classroom.CHANNEL
import smart.com.classroom.RTC_TOKEN
import smart.com.classroom.RTM_TOKEN
import smart.com.classroom.UID
import smart.com.classroom.homework.ClassRoomData
import smart.com.classroom.homework.HomeWork
import smart.com.classroom.homework.HomeworkRes
import smart.com.classroom.homework.KeyValue

class ClassRoomRepository {

    lateinit var classRMdata :ClassRoomData

    fun getClassRoomData():ClassRoomData{
        classRMdata = ClassRoomData(
            RTC_TOKEN,
            RTM_TOKEN,
            uid = UID,
            channelName = CHANNEL,
            showHomeWork = getShowHomeWork(),
            homeworkRes = getHomeworkRes(),
            extra = null
        )

        return classRMdata
    }

    private fun getHomeworkRes(): HomeworkRes {

        return HomeworkRes(0,getHomeworks())
    }

    private fun getShowHomeWork(): List<KeyValue> {
        return mutableListOf(
            KeyValue(5.toString(),3.toString()),
            KeyValue(10.toString(),4.toString()),
            KeyValue(15.toString(),5.toString()),
            KeyValue(25.toString(),6.toString()),
            KeyValue(35.toString(),1.toString()),
            KeyValue(45.toString(),1.toString()),
            KeyValue(65.toString(),2.toString())
        )
    }

    /**
     * 获取时间节点
     */
    fun getTimeStap():List<Int> {
        var timeList = mutableListOf<Int>()
        classRMdata.showHomeWork.map {
            timeList.add(it.key.toInt())
        }
        return  timeList
    }

    /**
     * 根据时间节点查找试题的id
     */
    fun findShowHomeworkId(time:Int):Int?= classRMdata.showHomeWork.find { it.key.toInt()==time }?.value?.toInt()

    /**
     * 根据时间节点查找对应展示的试题
     */
    fun findShowHomeWork(time:Int):HomeWork? = findShowHomeworkId(time)?.let {findHomeWorkById(it)}

    fun findFirstHomeworkShowTime():Int ?= classRMdata.showHomeWork?.firstOrNull()?.key?.toInt()

    fun findHomeWorkById(id:Int):HomeWork? = classRMdata.homeworkRes.homeWorks.find { it.id==id }

    private fun getHomeworks():List<HomeWork>{

        var homeWorks = mutableListOf<HomeWork>()
        for (i in 0 .. 10){
            homeWorks.add(
                HomeWork(0,"title-desc$i",i,"title$i","",15,
                choices = arrayListOf(
                    KeyValue("A","handbag$i"),
                    KeyValue("B","pencil$i"),
                    KeyValue("C","school$i")
                ),answer = KeyValue("A","handbag$i")
                ))
        }
        return homeWorks
    }
}