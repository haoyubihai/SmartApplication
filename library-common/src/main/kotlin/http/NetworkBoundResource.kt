package jrhlive.com.jbase.http

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.android.example.github.api.ApiEmptyResponse
import com.android.example.github.api.ApiErrorResponse
import com.android.example.github.api.ApiResponse
import com.android.example.github.api.ApiSuccessResponse
import utils.AppExecutors


/**
 * desc:
 * Created by jiarh on 2019-06-11 14:38.
 */
abstract class NetworkBoundResource <ResultType,RequestType>  @MainThread constructor(private val appExecutors: AppExecutors){
    private val result = MediatorLiveData<Resource<ResultType>>()
    init {
        result.value = Resource.loading(null)
        val dbSource = loadFromDb()
        result.addSource(dbSource){ data->
            result.removeSource(dbSource)
            if (shouldFetch(data)){
                fetchFromNetWork(dbSource)
            }else{
                result.addSource(dbSource){newData->
                    setValue(Resource.success(newData))

                }
            }
        }
    }


    private fun setValue(newValue:Resource<ResultType>){
        if (result.value!=newValue){
            result.value = newValue
        }
    }

    private fun fetchFromNetWork(dbSource: LiveData<ResultType>) {

        val apiResponse = createCall()

        result.addSource(dbSource){newData->
            setValue(Resource.loading(newData))
        }

        result.addSource(apiResponse){response->
            result.removeSource(dbSource)
            result.removeSource(apiResponse)
            when(response){
                is ApiSuccessResponse->{
                    appExecutors.diskIO().execute {
                        saveCallResult(processResponse(response))
                        appExecutors.mainThread().execute{
                            result.addSource(loadFromDb()){newData->
                                setValue(Resource.success(newData))
                            }
                        }
                    }
                }
                is ApiEmptyResponse->{
                    appExecutors.mainThread().execute {
                        result.addSource(loadFromDb()){newData->
                            setValue(Resource.success(newData))
                        }
                    }
                }
                is ApiErrorResponse->{
                    onFetchFailed()
                    result.addSource(dbSource){newData->
                        setValue(Resource.error(response.errorMessage,newData))
                    }
                }
            }
        }

    }


    protected open fun onFetchFailed(){}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected open fun processResponse(response:ApiSuccessResponse<RequestType>) = response.body

    @WorkerThread
    protected abstract fun saveCallResult(item:RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?):Boolean

    @MainThread
    protected abstract fun loadFromDb():LiveData<ResultType>

    @MainThread
    protected abstract fun createCall():LiveData<ApiResponse<RequestType>>


}