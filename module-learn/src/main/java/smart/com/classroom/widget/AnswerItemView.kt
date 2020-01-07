package smart.com.classroom.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.choice_item_view.view.*
import smart.com.R
import smart.com.classroom.homework.KeyValue

class AnswerItemView: FrameLayout{
    constructor(context: Context):this(context, null)
    constructor(context: Context, attrs: AttributeSet?):super(context, attrs){
        View.inflate(context, R.layout.choice_item_view,this)

    }

    fun initView(keyValue: KeyValue):AnswerItemView{
        tvAnswer.text = keyValue.key
        tvAnswerItem.text = keyValue.value
        setChoiceChecked(false)
        return  this
    }

    fun setChoiceChecked(checked:Boolean){
        ivArrow.visibility = if (checked) View.VISIBLE else View.GONE
        choiceBg.setBackgroundResource(if (checked)R.drawable.choice_checked else R.drawable.choice_normal)
        tvAnswer.setBackgroundResource(if (checked)R.drawable.answer_bg_checked else R.drawable.answer_bg_normal)
        tvAnswer.setTextColor(if (checked) ContextCompat.getColor(context,R.color.answer_tv_color_checked) else ContextCompat.getColor(context,R.color.answer_tv_color_normal))
        tvAnswerItem.setTextColor(if (checked) ContextCompat.getColor(context,R.color.white) else ContextCompat.getColor(context,R.color.answer_item_color))
    }

}

