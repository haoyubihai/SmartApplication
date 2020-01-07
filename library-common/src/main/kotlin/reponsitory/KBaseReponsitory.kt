package reponsitory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import jrhlive.com.jbase.http.NetworkBoundResource
import utils.AppExecutors


/**
 * desc: requestType 与 resultType 相同情况下，若不采用数据库缓存，使用
 * Created by jiarh on 2019-06-25 10:05.
 */
abstract class KBaseNoDbRepository<T>:KBaseRepository<T>(){

    private var itemRes : T? =null
    override fun saveCallResult(item: T) {
        itemRes = item
    }

    override fun shouldFetch(data: T?) = true

    override fun loadFromDb(): LiveData<T> {
      return  MutableLiveData<T>().apply { postValue(itemRes) }
    }

}

/**
 * repository base
 */
abstract class KBaseRepository<T>:KBaseParentRepository<T, T>(AppExecutors())

/**
 * baseParent
 */
abstract class KBaseParentRepository<T,R> (appExecutors: AppExecutors) :NetworkBoundResource<T,R>(appExecutors)
