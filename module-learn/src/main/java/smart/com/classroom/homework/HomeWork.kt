package smart.com.classroom.homework

import com.alibaba.fastjson.JSON
import com.google.gson.Gson


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
    val typeDesc: String,
    /** id */
    val id: Int=-1,
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
    val homeWorks: List<HomeWork>
)

/** 页面开始加载*/
const val WEB_LOAD_BEGIN= -1
/** 倒计时结束*/
const val WEB_RES_TIME_OVER = 0
/** 答题结束*/
const val WEB_RES_CHOICE_RESULT = 1


/**
 * web 交互返回的实体
 *
 */
data class WebCallRes(val type: Int, val extra: List<KeyValue>?)

class JSONHelper {
    companion object {
        fun toJSON(o:Any) = Gson().toJson(o)

    }
}


