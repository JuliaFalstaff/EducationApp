package com.example.educationapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.educationapp.model.AppState
import com.example.educationapp.model.repository.IRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repo: IRepository
) : ViewModel() {

    fun getLiveData(): LiveData<AppState> = liveDataToObserve
    private val compositeDisposable = CompositeDisposable()

    fun loadClassesData() {
        Thread {
            liveDataToObserve.postValue(AppState.SuccessClasses(repo.getData()))
        }.start()

    }

    fun loadHomeworkData() {
        liveDataToObserve.postValue(AppState.SuccessHomeWorks(repo.getData().homeWork))
    }

}