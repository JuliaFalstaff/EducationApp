package com.example.educationapp.di

import com.example.educationapp.model.datasource.DataSourceImpl
import com.example.educationapp.model.repository.IRepository
import com.example.educationapp.model.repository.RepositoryImpl
import com.example.educationapp.ui.ClassesFragment
import com.example.educationapp.ui.MainFragment
import com.example.educationapp.viewmodel.ClassesFragmentViewModel
import com.example.educationapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single<IRepository> { RepositoryImpl(datasource = DataSourceImpl()) }

}

val mainScreen = module {
    scope<MainFragment> {
        viewModel { MainViewModel(repo = get()) }
    }
}


val classScreen = module {
    scope<ClassesFragment> {
        viewModel { ClassesFragmentViewModel(repo = get()) }
    }
}

