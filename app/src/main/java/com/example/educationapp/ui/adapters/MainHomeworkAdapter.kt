package com.example.educationapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.educationapp.R
import com.example.educationapp.databinding.ItemHomeworkRvBinding
import com.example.educationapp.model.data.HomeWorks
import com.example.educationapp.utils.getCurrentDate

class MainHomeworkAdapter(
        var homeworkList: List<HomeWorks>,
) : RecyclerView.Adapter<MainHomeworkAdapter.HomeworkViewHolder>() {

    fun setData(list: List<HomeWorks>) {
        homeworkList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeworkViewHolder {
        return HomeworkViewHolder(
                ItemHomeworkRvBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: HomeworkViewHolder, position: Int) {
        holder.bind(homeworkList[position])
    }

    override fun getItemCount(): Int = homeworkList.size

    inner class HomeworkViewHolder(val binding: ItemHomeworkRvBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(homework: HomeWorks) = with(binding) {
            homeworkTitle.text = homework.titleLesson
            homeworkTime.text = homework.time
            if (homework.time > getCurrentDate()) {
                homeworkTime.setBackgroundResource(R.color.gradient_green_center)
            } else {
                homeworkTime.setBackgroundResource(R.color.design_default_color_error)
            }
            descriptionHWTextView.text = homework.descriptionHomework
        }
    }
}