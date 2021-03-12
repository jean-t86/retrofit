package au.com.example.retrofit.util

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ApiResponseCallAdapter<R>(private val responseType: Type) :
    CallAdapter<R, Call<LiveData<ApiResponse<R>>>> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): Call<LiveData<ApiResponse<R>>> {
        return ApiResponseCall(call)
    }
}
