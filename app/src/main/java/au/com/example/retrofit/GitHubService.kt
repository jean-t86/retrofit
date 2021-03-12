package au.com.example.retrofit

import au.com.example.retrofit.util.ApiResponse
import au.com.example.retrofit.util.ApiResponseAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://api.github.com/"

interface GitHubService {
    @GET("users/{user}/repos")
    suspend fun listRepos(@Path("user") user: String): ApiResponse<List<Repo>>
}

fun getGitHubService(): GitHubService {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(ApiResponseAdapterFactory())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    return retrofit.create(GitHubService::class.java)
}
