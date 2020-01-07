package smart.com

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("smart.com", appContext.packageName)
    }

    @Test
    fun testStackQueue(){
        var squeue = TwoStackToQueue<Int>()
        squeue.offer(1)
        squeue.offer(2)
        squeue.offer(3)
        squeue.offer(4)
        squeue.offer(5)

        print(squeue.peek())
        println(squeue.poll().toString())
        println(squeue.poll().toString())
        println(squeue.poll().toString())
        println(squeue.poll().toString())
        println(squeue.empty())
        println(squeue.poll().toString())
        println(squeue.empty())
    }
}
