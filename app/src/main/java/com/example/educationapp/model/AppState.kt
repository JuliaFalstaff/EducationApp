package com.example.educationapp.model

import com.example.educationapp.model.data.Classes
import com.example.educationapp.model.data.HomeWorks
import com.example.educationapp.model.data.Lessons

sealed class AppState {
    data class SuccessClasses(val data: Classes): AppState()
    data class SuccessHomeWorks(val data: List<HomeWorks>): AppState()
    data class SuccessLessons(val data: List<Lessons>): AppState()
    data class Error(val error: Throwable): AppState()
    object Loading: AppState()
}
