package api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Url





interface WgerApi {
    @Headers("Authorization:11d42ecb6e249ab8dd1f7bcd619ed6fa5da45b3d")
    @GET("exercise?language=2")
    fun fetchWorkouts(): Call<WgerResponse>

}