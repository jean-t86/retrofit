package au.com.example.retrofit.util

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ApiResponseAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val observerType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawObserverType = getRawType(observerType)
        if (getRawType(returnType) != Call::class.java &&
            rawObserverType != LiveData::class.java) {
            return null
        }

        val observableType = getParameterUpperBound(0, observerType as ParameterizedType)
        val rawObservableType = getRawType(observableType)
        if (rawObservableType != ApiResponse::class.java) {
            throw IllegalArgumentException("type must be a resource")
        }
        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("resource must be parameterized")
        }
        val bodyType = getParameterUpperBound(0, observableType)
        return ApiResponseCallAdapter<Any>(bodyType)
    }
}
