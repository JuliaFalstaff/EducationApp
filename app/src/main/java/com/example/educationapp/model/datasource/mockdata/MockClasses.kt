package com.example.educationapp.model.datasource.mockdata

import com.example.educationapp.model.data.Classes
import com.example.educationapp.model.data.HomeWorks
import com.example.educationapp.model.data.Lessons

data class MockClasses(
        val lessons: List<Lessons> = getMockLessons(),
        val homeWork: List<HomeWorks> = getMockHomeWorks()
)


