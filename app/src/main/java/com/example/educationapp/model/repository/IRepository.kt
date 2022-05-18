package com.example.educationapp.model.repository

import com.example.educationapp.model.data.Classes

interface IRepository {
    fun getData(): Classes
}