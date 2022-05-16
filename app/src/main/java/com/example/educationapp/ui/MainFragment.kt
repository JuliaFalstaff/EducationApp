package com.example.educationapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.educationapp.databinding.FragmentMainBinding
import com.example.educationapp.model.AppState
import com.example.educationapp.ui.adapters.IOnSkypeClickListener
import com.example.educationapp.ui.adapters.MainHomeworkAdapter
import com.example.educationapp.ui.adapters.MainLessonsAdapter
import com.example.educationapp.utils.*
import com.example.educationapp.viewmodel.MainViewModel
import org.koin.androidx.scope.createScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.inject
import org.koin.core.scope.Scope
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : Fragment(), KoinScopeComponent {

    override val scope: Scope by lazy { createScope(this) }
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    val viewModel: MainViewModel by inject()
    private var timer: CountDownTimer? = null
    private var adapter: MainLessonsAdapter? = null
    private var adapterHomework: MainHomeworkAdapter? = null
    private var currentTime: String = getCurrentDate()
    private val listener: IOnSkypeClickListener = object : IOnSkypeClickListener {
        override fun onClick(skype: View) {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(SKYPE_URL)
            }
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()

        val dateOfExam = "13-06-2022 20:30:00"
        val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        val ex = formatter.parse(dateOfExam).time
        val currentTime = getCurrentDateInMillis()
        val diff = ex?.minus(currentTime)
        diff?.let { setCount(it) }
    }

    private fun setCount(diff: Long) {
        object : CountDownTimer(diff, 1000) {

            override fun onTick(millisUntilFinished: Long) = with(binding.timerLayout) {
                val dateStr = Date(millisUntilFinished).convertToStringForTimer()
                dayOne.text = dateStr[0].toString()
                dayTwo.text = dateStr[1].toString()
                hoursOne.text = dateStr[3].toString()
                hoursTwo.text = dateStr[4].toString()
                minOne.text = dateStr[6].toString()
                minTwo.text = dateStr[7].toString()

            }

            override fun onFinish() {
                Log.d("TAG", "onFinish")
            }
        }.start()
    }


    private fun initViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.loadClassesData()
    }

    private fun renderData(state: AppState?) {
        when (state) {
            is AppState.SuccessClasses -> {
                val lessonList = state.data
                binding.dailyLessonRecyclerView.adapter = MainLessonsAdapter(lessonList.lessons, listener)
                adapter.let {
                    it?.setData(lessonList.lessons)
                }

                binding.homeworkRecyclerView.adapter = MainHomeworkAdapter(lessonList.homeWork)
                adapterHomework.let {
                    it?.setData(lessonList.homeWork)
                }
            }
            is AppState.Error -> {
                Toast.makeText(
                    requireContext(),
                    "Error: ${state.error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroy() {
        timer?.cancel()
        _binding = null
        super.onDestroy()
    }

    companion object {
        fun newInstance() = MainFragment()
        const val SKYPE_URL = "https://www.skype.com/ru/"
    }
}



