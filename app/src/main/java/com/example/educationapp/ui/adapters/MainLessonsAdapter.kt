package com.example.educationapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.educationapp.databinding.ItemLessonsRvBinding
import com.example.educationapp.model.data.Lessons

class MainLessonsAdapter(
        var lessonsList: List<Lessons>,
        val listener: IOnSkypeClickListener,
) : RecyclerView.Adapter<MainLessonsAdapter.LessonsViewHolder>() {

    fun setData(list: List<Lessons>) {
        lessonsList = list.sortedBy { it.date }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsViewHolder {
        return LessonsViewHolder(
                ItemLessonsRvBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: LessonsViewHolder, position: Int) {
        holder.bind(lessonsList[position])
    }

    override fun getItemCount(): Int = lessonsList.size

        inner class LessonsViewHolder(val binding: ItemLessonsRvBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(lesson: Lessons) = with(binding) {
            lessonTitle.text = lesson.title
            lessonTime.text = lesson.date
            skypeView.setOnClickListener {
                listener.onClick(it)
            }
        }
    }
}