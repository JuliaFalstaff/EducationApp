package com.example.educationapp.model.datasource.mockdata

import com.example.educationapp.model.data.HomeWorks

fun getMockHomeWorks(): List<HomeWorks> = listOf(
        HomeWorks(
                titleLesson = "History",
                time = "14-05-2022 08:30",
                descriptionHomework = "do some homework: read about ancient times"
        ),
        HomeWorks(
                titleLesson = "Maths",
                time = "12-05-2022 08:30",
                descriptionHomework = "do some homework: factorials and algorithms"
        ),
        HomeWorks(
                titleLesson = "Literature",
                time = "20-05-2022 15:30",
                descriptionHomework = "do some homework: read Dostoevsky"
        ),

        HomeWorks(
                titleLesson = "Physics",
                time = "16-05-2022 15:30",
                descriptionHomework = "do some exercise at home"
        )
)