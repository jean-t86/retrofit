package au.com.example.retrofit.util

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiResponseCall<R>(private val call: Call<R>) : Call<ApiResponse<R>> {

    override fun enqueue(callback: Callback<ApiResponse<R>>) {
        call.enqueue(object : Callback<R> {
            override fun onResponse(call: Call<R>, response: Response<R>) {
                callback.onResponse(
                    this@ApiResponseCall,
                    Response.success(ApiResponse.create(response)))
            }

            override fun onFailure(call: Call<R>, throwable: Throwable) {
                callback.onResponse(
                    this@ApiResponseCall,
                    Response.success(ApiResponse.create(throwable)))
            }
        })
    }

    override fun clone(): Call<ApiResponse<R>> = ApiResponseCall(call.clone())

    override fun execute(): Response<ApiResponse<R>> {
        throw UnsupportedOperationException("ApiResponseCall doesn't support execute")
    }

    override fun isExecuted(): Boolean = call.isExecuted

    override fun cancel() = call.cancel()

    override fun isCanceled(): Boolean = call.isCanceled

    override fun request(): Request = call.request()

    override fun timeout(): Timeout = call.timeout()

}
