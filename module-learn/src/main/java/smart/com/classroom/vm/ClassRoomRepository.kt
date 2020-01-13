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

    lateinit var classRMdata: ClassRoomData

    val homeWork0 = HomeWork(
        0,
        "看问题选答案",
        0,
        "以下画线部分，用作宾语的是？\n You may leave.This is my private party！the lady said.",
        "",
        15,
        choices = arrayListOf(
            KeyValue("A", "You may leave.This is my private party！"),
            KeyValue("B", "the lady"),
            KeyValue("C", "said")
        ),
        answer = KeyValue("A", "You may leave.This is my private party！")
    )
    val homeWork1 = HomeWork(
        0, "看问题选答案", 1, "提问：下面哪个句子是陈述句 ？", "", 15,
        choices = arrayListOf(
            KeyValue("A", "Why was the writer so angry?"),
            KeyValue("B", "Did the writer enjoy this play?"),
            KeyValue("C", "Can I help you?"),
            KeyValue("D", "Last week, I saw a play. ")

        ), answer = KeyValue("B", "Did the writer enjoy this play?")
    )

    val homeWork2 = HomeWork(
        0, "看问题选答案", 2, "You may leave. This is my private party!\n the lady said _____.", "", 15,
        choices = arrayListOf(
            KeyValue("A", "happily"),
            KeyValue("B", "angrily"),
            KeyValue("C", "friendly"),
            KeyValue("D", "lovely")
        ), answer = KeyValue("B", "angrily")
    )



    val homeWork3 = HomeWork(
        0, "看问题选答案", 3, "下列句子是 “ 主 + 谓  ” 结构的是 ", "", 15,
        choices = arrayListOf(
            KeyValue("A", "She runs fast. "),
            KeyValue("B", "I love my mom."),
            KeyValue("C", "I am a student.")
        ), answer = KeyValue("A", "She runs fast.")
    )

    val homeWork4 = HomeWork(
        0, "看问题选答案", 4, "下列句子是 “ 主 + 谓 + 宾  ” 结构的是 ", "", 15,
        choices = arrayListOf(
            KeyValue("A", "My name is Noah."),
            KeyValue("B", "In the end, the man came in."),
            KeyValue("C", "I saw a play yesterday")
        ), answer = KeyValue("C", "I saw a play yesterday")
    )


    fun getClassRoomData(): ClassRoomData {
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

        return HomeworkRes(0, getHomeworks())
    }

    private fun getShowHomeWork(): List<KeyValue> {
        return mutableListOf(
            KeyValue(385.toString(), 0.toString()),
            KeyValue(435.toString(), 1.toString()),
            KeyValue(966.toString(), 2.toString()),
            KeyValue(985.toString(), 3.toString()),
            KeyValue(1021.toString(), 4.toString())

        )
    }


    /**
     * 获取时间节点
     */
    fun getTimeStap(): List<Int> {
        var timeList = mutableListOf<Int>()
        classRMdata.showHomeWork.map {
            timeList.add(it.key.toInt())
        }
        return timeList
    }

    /**
     * 根据时间节点查找试题的id
     */
    fun findShowHomeworkId(time: Int): Int? =
        classRMdata.showHomeWork.find { it.key.toInt() == time }?.value?.toInt()

    /**
     * 根据时间节点查找对应展示的试题
     */
    fun findShowHomeWork(time: Int): HomeWork? =
        findShowHomeworkId(time)?.let {
            findHomeWorkById(it)
        }

    fun findFirstHomeworkShowTime(): Int? = classRMdata.showHomeWork?.firstOrNull()?.key?.toInt()

    fun findHomeWorkById(id: Int): HomeWork? =
        classRMdata.homeworkRes.homeWorks.find { it.id == id }

    private fun getHomeworks(): List<HomeWork> {

        var homeWorks = mutableListOf<HomeWork>()

        homeWorks.add(homeWork0)
        homeWorks.add(homeWork1)
        homeWorks.add(homeWork2)
        homeWorks.add(homeWork3)
        homeWorks.add(homeWork4)

        return homeWorks
    }
}