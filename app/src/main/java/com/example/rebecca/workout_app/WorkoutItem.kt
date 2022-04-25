package com.example.rebecca.workout_app

import com.google.gson.annotations.SerializedName

data class WorkoutItem (
    var name: String="",
    var description:String="",
    var id: String="",
    var muscle: List<String>,
    var category: String="",
    @SerializedName("image_url_main") var url_m: String = "",
    @SerializedName("image_url_secondary") var url_s: String = ""


)

