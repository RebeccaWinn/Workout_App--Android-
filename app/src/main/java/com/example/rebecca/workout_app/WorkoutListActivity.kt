package com.example.rebecca.workout_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class WorkoutListActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
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
}