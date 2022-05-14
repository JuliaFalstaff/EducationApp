package com.example.educationapp.model.datasource

import com.example.educationapp.model.data.Classes

interface IDataSource {
    fun getData(): Classes
}