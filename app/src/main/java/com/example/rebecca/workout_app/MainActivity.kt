package com.example.rebecca.workout_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import java.util.*

class MainActivity: AppCompatActivity(), WorkoutListFragment.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_list)

        val isFragmentContainerEmpty = savedInstanceState == null
        if(isFragmentContainerEmpty){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, WorkoutListFragment.newInstance())
                .commit()
        }
    }

    override fun onWorkoutItemSelected(workoutItem:WorkoutItem){
        Log.d("MainActivity","Workout item was selected: ${workoutItem.id}")
        val fragment = WorkoutItemFragment.newInstance(workoutItem)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer,fragment)
            .addToBackStack(null)
            .commit()
    }

}