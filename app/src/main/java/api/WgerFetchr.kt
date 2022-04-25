package api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rebecca.workout_app.WorkoutItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG ="WgerFetchr"

class WgerFetchr {
    private val wgerApi: WgerApi

    init{
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://wger.de/api/v2/")

            .addConverterFactory(GsonConverterFactory.create())
            .build()
        wgerApi = retrofit.create(WgerApi::class.java)

    }
    fun fetchWorkouts(): LiveData<List<WorkoutItem>> {
        val responseLiveData:MutableLiveData<List<WorkoutItem>> = MutableLiveData()
        val wgerRequest: Call<WgerResponse> = wgerApi.fetchWorkouts()

        wgerRequest.enqueue(object: Callback<WgerResponse> {

            override fun onFailure(call: Call<WgerResponse>, t:Throwable){
                Log.e(TAG, "Failed to fetch workouts", t)
            }

            override fun onResponse(
                call: Call<WgerResponse>,
                response: Response<WgerResponse>
            ){
                Log.d (TAG, "Response received")
                val wgerResponse : WgerResponse? = response.body()
                val workoutList : List<WorkoutItem> = wgerResponse?.workoutList
                    ?: mutableListOf()
//                workoutList= workoutList.filterNot{
////                    it.url.isBlank()
//                }
                responseLiveData.value= workoutList
            }
        })

        return responseLiveData
    }

}