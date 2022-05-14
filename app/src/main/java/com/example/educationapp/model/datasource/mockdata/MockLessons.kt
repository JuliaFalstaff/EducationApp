package com.example.educationapp.model.datasource.mockdata

import com.example.educationapp.model.data.Lessons


fun getMockLessons(): List<Lessons> = listOf(
        Lessons(
                title = "Literature",
                date = "14-05-2022 08:30",
                description = "sample lesson description"
        ),
        Lessons(
                title = "History",
                date = "14-05-2022 18:30",
                description = "sample lesson description"
        ),
        Lessons(
                title = "Maths",
                date = "16-05-2022 12:30",
                description = "sample lesson description"
        ),
        Lessons(
                title = "Physics",
                date = "14-05-2022 19:30",
                description = "sample lesson description"
        )
)
