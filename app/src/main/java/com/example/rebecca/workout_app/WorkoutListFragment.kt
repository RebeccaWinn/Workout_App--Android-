package com.example.rebecca.workout_app

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

private const val TAG = "WorkoutListFragment"


class WorkoutListFragment : Fragment() {
    private lateinit var workoutListViewModel: WorkoutListViewModel
    private lateinit var workoutRecyclerView: RecyclerView

    interface Callbacks {
        fun onWorkoutItemSelected(workoutItem: WorkoutItem)
    }
    private var callbacks: Callbacks? = null

    override fun onAttach(context: Context){
        super.onAttach(context)
        callbacks= context as Callbacks?
    }


    override fun onDetach(){
        super.onDetach()
        callbacks = null
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        workoutListViewModel =
            ViewModelProviders.of(this).get(WorkoutListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_workout_list, container, false)
        workoutRecyclerView = view.findViewById(R.id.workout_recycler_view)
        workoutRecyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        workoutListViewModel.workoutItemLiveData.observe(
            viewLifecycleOwner,
            Observer { workoutList ->
                workoutRecyclerView.adapter = workoutAdapter(workoutList)


            })
    }


    private inner class WorkoutHolder(itemView:View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private lateinit var workoutItem: WorkoutItem
        private val titleTextView: TextView = itemView.findViewById(R.id.TextView)
        init{
            itemView.setOnClickListener(this)
        }

        fun bind(workoutItem: WorkoutItem){
            this.workoutItem = workoutItem
            titleTextView.text= workoutItem.name
        }

        override fun onClick(v:View?){
            callbacks?.onWorkoutItemSelected(workoutItem)
        }

    }


    private inner class workoutAdapter(private val workoutList: List<WorkoutItem>)
        : RecyclerView.Adapter<WorkoutHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutHolder {
            val view = layoutInflater.inflate(R.layout.item_workout, parent, false)
            return WorkoutHolder(view)
        }

        override fun getItemCount(): Int = workoutList.size

        override fun onBindViewHolder(holder: WorkoutHolder, position: Int) {
            val workoutItem = workoutList[position]
            holder.bind(workoutItem)
            if(position %2 == 1)
            {
                holder.itemView.setBackgroundColor(Color.parseColor("#66FFFFFF"));
            }
            else
            {
                holder.itemView.setBackgroundColor(Color.parseColor("#8A7A7676"))
            }
        }
    }

    companion object {
        fun newInstance(): WorkoutListFragment{
            return WorkoutListFragment()
        }
    }
}