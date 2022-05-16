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
//        setTimer()

        val dateOfExam = "13-06-2022 20:30:00"
        val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        val ex = formatter.parse(dateOfExam).time
        val currentTime = getCurrentDateInMillis()
        val diff = ex?.minus(currentTime)
        diff?.let { setCount(it) }
    }

    private fun setCount(diff: Long) {
        object : CountDownTimer(diff, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                val dateStr = Date(millisUntilFinished).convertToStringForTimer()
                val day1 = dateStr[0].toString()
                val day2 = dateStr[1].toString()
                val hours1 = dateStr[3].toString()
                val hours2 = dateStr[4].toString()
                val minutes1 = dateStr[6].toString()
                val minutes2 = dateStr[7].toString()
                binding.textTimer.setText("days: $day1 $day2 hours: $hours1 $hours2 minutes: $minutes1 $minutes2 " )
            }

            override fun onFinish() {
                binding.textTimer.setText("done!")
            }
        }.start()
    }


    @SuppressLint("SimpleDateFormat")
    private fun setTimer() {
        val dateOfExam = "13-06-2022 20:30:00"
//        val examConvert= dateOfExam.convertStringToDateOldApi()?.time
        val currentTime = getCurrentDateInMillis()


            val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")


        val ex = formatter.parse(dateOfExam).time
        val diff = ex?.minus(currentTime)
//        diff?.let { startCountDownTimer(it) }

        Log.d("TAG 777", "$ex + $currentTime + $diff")


    }

    private fun startCountDownTimer(timeMillis: Long) {
        timer = object : CountDownTimer(timeMillis, 1) {
            override fun onTick(millisUntilFinished: Long)  {

                val day = millisUntilFinished / (1000 * 60 * 60 *24)
                val hours = millisUntilFinished / ((1000 * 60 * 60) % 24)
                val minutes = millisUntilFinished / ((1000 * 60) % 60)
                Log.d("TAG 777", "$day + $hours + $minutes")
                val dateStr = Date(millisUntilFinished).convertToString()
                binding.textTimer.text = dateStr.toString()
//                dayTwo.text = ""
//                hoursOne.text = "5"
//                 hoursTwo.text = ""
//                minOne.text = ""
//                minTwo.text = ""

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



