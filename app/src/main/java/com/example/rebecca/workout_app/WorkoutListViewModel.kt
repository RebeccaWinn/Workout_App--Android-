package com.example.rebecca.workout_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import api.WgerFetchr

class WorkoutListViewModel:ViewModel() {
    val workoutItemLiveData: LiveData<List<WorkoutItem>>
    init{
        workoutItemLiveData= WgerFetchr().fetchWorkouts()
    }
}