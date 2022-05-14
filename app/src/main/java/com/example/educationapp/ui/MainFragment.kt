package com.example.educationapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
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
import com.example.educationapp.viewmodel.MainViewModel
import org.koin.androidx.scope.createScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class MainFragment : Fragment(), KoinScopeComponent {

    override val scope: Scope by lazy { createScope(this) }
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    val viewModel: MainViewModel by inject()
    private var adapter: MainLessonsAdapter? = null
    private var adapterHomework: MainHomeworkAdapter? = null
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
    }

    private fun initViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {renderData(it)})
        viewModel.loadClassesData()
    }

    private fun renderData(state: AppState?) {
        when (state) {
            is AppState.SuccessClasses -> {
                val lessonList = state.data
                binding.dailyLessonRecyclerView.adapter = lessonList.lessons.let {
                    MainLessonsAdapter(it, listener)
                }
                adapter.let {
                    it?.setData(lessonList.lessons)
                }
                binding.homeworkRecyclerView.adapter = lessonList.homeWork.let {
                    MainHomeworkAdapter(it)
                }
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
        _binding = null
        super.onDestroy()
    }

    companion object {
        fun newInstance() = MainFragment()
        const val SKYPE_URL = "https://www.skype.com/ru/"
    }
}



