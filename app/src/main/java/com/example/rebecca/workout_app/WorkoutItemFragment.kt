package com.example.rebecca.workout_app


import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


private const val ARG_WORKOUT_ITEM_TITLE = "workout_item_title"
private const val ARG_WORKOUT_ITEM_DESCRIPTION = "workout_item_description"


class WorkoutItemFragment: Fragment() {

    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var timeTextView:TextView
    private lateinit var timerButton: Button

    private lateinit var workoutItemTitle: String
    private lateinit var workoutItemDescription: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        workoutItemTitle = arguments?.getSerializable(ARG_WORKOUT_ITEM_TITLE) as String
        workoutItemDescription = arguments?.getSerializable(ARG_WORKOUT_ITEM_DESCRIPTION) as String
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_workout_item, container, false)

        titleTextView = view.findViewById(R.id.titleTextView)
        descriptionTextView= view.findViewById(R.id.descriptionTextView)
        timeTextView= view.findViewById(R.id.timer_text)
        timerButton= view.findViewById(R.id.timer)


        return view
    }

//    fun countdown(){
//        object : CountDownTimer(3000, 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//
//                timeTextView.text = (millisUntilFinished / 1000).toString()
//            }
//            override fun onFinish() {
//                timeTextView.text = "done!"
//            }
//        }.start()
//    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    val mp: MediaPlayer = MediaPlayer.create(getActivity(), R.raw.ding)

    titleTextView.text = workoutItemTitle
        descriptionTextView.text = workoutItemDescription
        timerButton.setOnClickListener{
            object : CountDownTimer(10000, 1000) {
                override fun onTick(millisUntilFinished: Long) {

                    timeTextView.text = (millisUntilFinished / 1000).toString()
                }
                override fun onFinish() {
                    timeTextView.text = "Done!"
                    mp.start()

                }
            }.start()
        }
    }


    private class PhotoHolder(photoImageView:ImageView): RecyclerView.ViewHolder(photoImageView){
        fun bindImageFromUrl(url:String){
            val imageView = itemView as ImageView
            Picasso.get()
                .load(url)
                .resize(400, 400)
                .centerCrop()
                .into(imageView)
            imageView.scaleType = ImageView.ScaleType.FIT_XY
        }

    }


    private class PhotoAdapter(private val workoutList: List<WorkoutItem>)
        : RecyclerView.Adapter<PhotoHolder>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): PhotoHolder {
            val imageView = ImageView(parent.context)
            return PhotoHolder(imageView)
        }

        override fun getItemCount(): Int = workoutList.size

        override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
            val workoutItem = workoutList[position]
            holder.bindImageFromUrl(workoutItem.url_m)
            holder.bindImageFromUrl(workoutItem.url_s)
        }
    }
    companion object {
        fun newInstance(workoutItem: WorkoutItem): WorkoutItemFragment {
            val fragment = WorkoutItemFragment()
            val arguments = Bundle()
            arguments.putSerializable(ARG_WORKOUT_ITEM_TITLE, workoutItem.name)
            arguments.putSerializable(ARG_WORKOUT_ITEM_DESCRIPTION, workoutItem.description)
            fragment.arguments = arguments

            return fragment
        }
    }
}