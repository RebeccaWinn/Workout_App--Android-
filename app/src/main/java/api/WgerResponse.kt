package api

import com.example.rebecca.workout_app.WorkoutItem
import com.google.gson.annotations.SerializedName
import retrofit2.http.Path

class WgerResponse {
    @SerializedName("results")
    lateinit var workoutList: List<WorkoutItem>
}