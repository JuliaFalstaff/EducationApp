package com.example.educationapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.educationapp.databinding.ItemClassesLessonCallBinding
import com.example.educationapp.databinding.ItemClassesLessonExtraBinding
import com.example.educationapp.databinding.ItemClassesLessonSimpleBinding
import com.example.educationapp.model.data.Lessons
import com.example.educationapp.utils.formatStringDateToTime

class ClassesFragmentAdapter(
        var classesList: List<Lessons>,
        val listener: IOnSkypeClickListener,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    fun setData(list: List<Lessons>) {
        classesList = list.sortedBy { it.date }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LESSON_CALL -> {
                val binding: ItemClassesLessonCallBinding = ItemClassesLessonCallBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false)
                ClassesViewHolder(binding)
            }
            LESSON_SIMPLE -> {
                val binding: ItemClassesLessonSimpleBinding = ItemClassesLessonSimpleBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false)
                SimpleLessonViewHolder(binding)
            }
            LESSON_EXTRA -> {
                val binding: ItemClassesLessonExtraBinding = ItemClassesLessonExtraBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false)
                ExtraLessonViewHolder(binding)
            }
            else -> throw RuntimeException(VIEWHOLDER_TYPE_ERROR)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = classesList[position]
        when (item.typeLesson) {
            LESSON_CALL -> {
                holder as ClassesViewHolder
                holder.bind(classesList[position])
            }
            LESSON_EXTRA -> {
                holder as ExtraLessonViewHolder
                holder.bind(classesList[position])
            }

            LESSON_SIMPLE -> {
                holder as SimpleLessonViewHolder
                holder.bind(classesList[position])
            }
            else -> throw RuntimeException(VIEWHOLDER_TYPE_ERROR)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (classesList[position].typeLesson) {
            0 -> LESSON_CALL
            2 -> LESSON_EXTRA
            else -> LESSON_SIMPLE
        }
    }

    override fun getItemCount(): Int = classesList.size

    inner class ClassesViewHolder(val binding: ItemClassesLessonCallBinding) :
            RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(lesson: Lessons) = with(binding) {
            lessonTitle.text = "Lesson: ${lesson.title}"
            lessonTime.text = "Date: ${lesson.date}"
            time.text = formatStringDateToTime(lesson.date)
            skypeView.setOnClickListener {
                listener.onClick(it)
            }
        }
    }

    inner class ExtraLessonViewHolder(val binding: ItemClassesLessonExtraBinding) :
            RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(lesson: Lessons) = with(binding) {
            lessonTitle.text = "Lesson: ${lesson.title}"
            lessonTime.text = "Date: ${lesson.date}"
            time.text = formatStringDateToTime(lesson.date)
        }
    }

    inner class SimpleLessonViewHolder(val binding: ItemClassesLessonSimpleBinding) :
            RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(lesson: Lessons) = with(binding) {
            lessonTitle.text = "Lesson: ${lesson.title}"
            lessonTime.text = "Date: ${lesson.date}"
            time.text = formatStringDateToTime(lesson.date)
        }
    }

    companion object {
        private const val LESSON_CALL = 0
        private const val LESSON_SIMPLE = 1
        private const val LESSON_EXTRA = 2
        private const val VIEWHOLDER_TYPE_ERROR = "Unknown type of viewholder"
    }
}