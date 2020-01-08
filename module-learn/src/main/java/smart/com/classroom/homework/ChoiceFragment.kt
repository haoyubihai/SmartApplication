package smart.com.classroom.homework


import android.os.Bundle
import android.view.View
import com.aly.phone.base.ui.BaseFragment
import com.jeremyliao.liveeventbus.LiveEventBus
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_choice.*
import smart.com.R
import smart.com.classroom.ui.CLOSE_CHOICE_FRAGMENT
import smart.com.classroom.widget.AnswerItemView
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * 选择题界面
 */


class ChoiceFragment : BaseFragment(), View.OnClickListener {
    override val layoutId: Int = R.layout.fragment_choice
    lateinit var subscribe: Disposable
     var abc = mutableListOf<AnswerItemView>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val homeWork: HomeWork = getHomeWork()
        Timber.e("homeworkJson="+ JSONHelper.toJson(homeWork))
        tvTitle.text = homeWork.title
        tvA.initView(homeWork.choices[0])
        tvB.initView(homeWork.choices[1])
        tvC.initView(homeWork.choices[2])

        abc.apply {
            add(tvA)
            add(tvB)
            add(tvC)
        }

        tvA.setOnClickListener(this)
        tvB.setOnClickListener(this)
        tvC.setOnClickListener(this)

        startClock()

    }

    private fun resetABC(){
        for (answerItemView in abc) {
            answerItemView.setChoiceChecked(false)
        }
    }

    private fun startClock() {
        subscribe = Observable.interval(1, TimeUnit.SECONDS).take(16)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                var time: Int = 15 - it.toInt()
                tvTime.text = if (time < 10) "00:0$time" else "00:$time"
                if (time == 0) {
                    LiveEventBus.get(CLOSE_CHOICE_FRAGMENT).post(
                        CLOSE_CHOICE_FRAGMENT
                    )
                }
            }
    }

    private fun getHomeWork(): HomeWork {
        var choices = mutableListOf<KeyValue>().apply {
            add(KeyValue("A", "handbag"))
            add(KeyValue("B", "pencil"))
            add(KeyValue("C", "school"))

        }
        return HomeWork(
            0, "desc",0, "What can you see in the picture ?", "http://ssss.jpg", 15, choices, choices[0]
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        subscribe.dispose()
    }

    override fun onClick(v: View?) {
        v as AnswerItemView
        resetABC()
        v.setChoiceChecked(true)
    }
}
