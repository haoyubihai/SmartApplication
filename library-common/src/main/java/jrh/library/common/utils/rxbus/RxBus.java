package jrh.library.common.utils.rxbus;

import com.jeremyliao.liveeventbus.LiveEventBus;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import jrh.library.common.utils.StringUtil;
import timber.log.Timber;

/**
 * desc:
 * Created by jiarh on 16/12/13 11:38.
 */

public class RxBus {
    private static volatile RxBus defaultInstance;

    private final Subject<Object> bus;

    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    private RxBus() {
        bus = PublishSubject.create().toSerialized();
    }

    // 单例RxBus
    public static RxBus getDefault() {
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RxBus();
                }
            }
        }
        return defaultInstance;
    }

//    public void postValue(@NotNull Event event) {
//        if (StringUtil.isBlank(event.code)) {
//            throw new NullPointerException("event.code 不能为空");
//        }
//        Timber.i("RxBus 发送通知事件" + event.code);
//        if (!onEventIntercepted(event)) {
//            LiveEventBus.get().with(event.code).post(event);
//        }
//    }

    /**
     * 对需要过滤的消息增加一次过滤操作
     *
     * @param event
     * @return
     */
    private boolean onEventIntercepted(Event event) {

        return false;
    }

//    public  void  toObservable(LifecycleOwner lifecycleOwner, @NotNull String key, Observer<Event> observer) {
//        if (StringUtil.isBlank(key)) {
//            throw new NullPointerException("key 不能为空");
//        }
//        LiveEventBus.get()
//                .with(key, Event.class)
//                .observe(lifecycleOwner, observer);
//    }

    @Deprecated
    // 发送一个新的事件
    public void post(Object o) {
        bus.onNext(o);
    }

    @Deprecated
    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Observable<T> toObservable(Class<T> eventType) {
        return bus.ofType(eventType)
                .map(new Function<T, T>() {
                    @Override
                    public T apply(T t) throws Exception {
                        if (t instanceof Event) {
                            Timber.i("通知事件：" + ((Event) t).code);
                        }
                        return t;
                    }
                }).observeOn(AndroidSchedulers.mainThread());
    }


}
