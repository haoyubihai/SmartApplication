package smart.com

import java.util.*

/**
 * Stack 先进后出
 * Queue 先进先出
 */
class TwoStackToQueue<E> {

    //负责入队
    var s1= Stack<E>()
    //负责出队
    var s2= Stack<E>()

    fun offer(e:E){
        s1.push(e)
    }

    fun poll():E{
        //1先将s1中的元素倒入s2
        while (s2.empty()){
            while (s1.isNotEmpty()){
                // pop 先peek 在弹栈返回弹出元素
                s2.push(s1.pop())
            }
        }
        val  e = s2.peek()
        s2.pop()

        while (s1.empty()){
            while (s2.isNotEmpty()){
                s1.push(s2.pop())
            }
        }
        return e;
    }

    fun peek():E{
        //1先将s1中的元素倒入s2
        while (s2.empty()){
            while (s1.isNotEmpty()){
                // pop 先peek 在弹栈返回弹出元素
                s2.push(s1.pop())
            }
        }
        val  e = s2.peek()
        while (s1.empty()){
            while (s2.isNotEmpty()){
                s1.push(s2.pop())
            }
        }
        return e
    }

    fun empty() = s1.empty()
}