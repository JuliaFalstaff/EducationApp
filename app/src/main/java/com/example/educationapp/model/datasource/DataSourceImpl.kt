package com.example.educationapp.model.datasource

import com.example.educationapp.model.data.Classes
import com.example.educationapp.model.datasource.mockdata.getMockHomeWorks
import com.example.educationapp.model.datasource.mockdata.getMockLessons

class DataSourceImpl() : IDataSource {
    override fun getData(): Classes {
        return Classes(getMockLessons(), getMockHomeWorks())
    }
}