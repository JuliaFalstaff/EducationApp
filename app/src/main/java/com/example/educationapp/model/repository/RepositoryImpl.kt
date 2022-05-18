package com.example.educationapp.model.repository

import com.example.educationapp.model.data.Classes
import com.example.educationapp.model.datasource.IDataSource

class RepositoryImpl(private val datasource: IDataSource): IRepository {
    override fun getData(): Classes {
       return datasource.getData()
    }
}