package smart.com.classroom.homework

import com.alibaba.fastjson.JSON


/** 拉取课程信息*/
data class ClassRoomData(
    /** rtcToken*/
    val rtcToken: String,
    /** rtmToken*/
    val rtmToken: String,
    /** uid*/
    val uid: Int,
    /** channelName*/
    val channelName: String,
    /** 展示作业的时间点 KeyValue --- key ->时间点  value->homeworkId*/
    val showHomeWork: List<KeyValue>,
    /** 相关的作业资源*/
    val homeworkRes: HomeworkRes,
    /** 扩展 目前null*/
    val extra: List<KeyValue>?
)

/** 作业*/
data class HomeWork(
    /** 作业类型 0 选择题，1语音题*/
    val type: Int,
    /** 作业类型描述 ：如看图选择答案*/
    val typeDesc:String,
    /** id */
    val id: Int,
    /** 标题*/
    val title: String,
    /** 内容 ,图片地址，或者文本信息 没有为null*/
    val content: String,
    /** 做题限制时间 单位 s*/
    val time: Int,
    /** 题目选项*/
    val choices: List<KeyValue>,
    /** 正确答案 */
    val answer: KeyValue
)

/** key-value*/
data class KeyValue(val key: String, val value: String)

/** 作业相关资源*/
data class HomeworkRes(
    /** 课程id*/
    val courseId: Int,
    /** 作业内容*/
    val homeWorks: List<HomeWork>)


class JSONHelper {
    companion object {
        fun toJson(homeWork: HomeWork) = JSON.toJSONStringWithDateFormat(homeWork, "yyyyMMdd")
    }
}


