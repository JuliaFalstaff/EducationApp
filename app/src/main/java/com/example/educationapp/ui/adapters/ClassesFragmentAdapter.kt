package com.example.educationapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.educationapp.databinding.ItemClassesLessonCallBinding
import com.example.educationapp.databinding.ItemLessonsRvBinding
import com.example.educationapp.model.data.Lessons

class ClassesFragmentAdapter(
    var classesList: List<Lessons>,
    val listener: IOnSkypeClickListener
) : RecyclerView.Adapter<ClassesFragmentAdapter.ClassesViewHolder>() {


    fun setData(list: List<Lessons>) {
        classesList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassesViewHolder {
        return ClassesViewHolder(
            ItemClassesLessonCallBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ClassesViewHolder, position: Int) {
        holder.bind(classesList[position])
    }

    override fun getItemCount(): Int = classesList.size

    inner class ClassesViewHolder(val binding: ItemClassesLessonCallBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(lesson: Lessons) = with(binding) {
            lessonTitle.text = lesson.title
            lessonTime.text = lesson.date
            time.text = lesson.date

            skypeView.setOnClickListener {
                listener.onClick(it)
            }
        }
    }
}