package au.com.example.retrofit.util

import android.util.Log
import androidx.lifecycle.LiveData
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiResponseCall<R>(private val call: Call<R>) : Call<LiveData<ApiResponse<R>>> {

    override fun enqueue(callback: Callback<LiveData<ApiResponse<R>>>) {
        call.enqueue(object : Callback<R> {
            override fun onResponse(call: Call<R>, response: Response<R>) {
                Log.d("", "")

                val liveData = object : LiveData<ApiResponse<R>>() {
                    override fun onActive() {
                        super.onActive()
                        value = ApiResponse.create(response)
                    }
                }

                Log.d("", "")

                callback.onResponse(
                    this@ApiResponseCall,
                    Response.success(liveData)
                )
            }

            override fun onFailure(call: Call<R>, throwable: Throwable) {
                Log.d("", "")
            }
        })
    }

    override fun clone(): Call<LiveData<ApiResponse<R>>> = ApiResponseCall(call.clone())

    override fun execute(): Response<LiveData<ApiResponse<R>>> {
        throw UnsupportedOperationException("ApiResponseCall doesn't support execute")
    }

    override fun isExecuted(): Boolean = call.isExecuted

    override fun cancel() = call.cancel()

    override fun isCanceled(): Boolean = call.isCanceled

    override fun request(): Request = call.request()

    override fun timeout(): Timeout = call.timeout()

}
