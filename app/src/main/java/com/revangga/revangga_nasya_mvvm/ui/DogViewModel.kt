package com.revangga.revangga_nasya_mvvm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revangga.revangga_nasya_mvvm.data.model.Dog
import com.revangga.revangga_nasya_mvvm.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {

    private val _dogsDatabase = MutableLiveData<List<Dog>>()
    val dogsDatabase: LiveData<List<Dog>> = _dogsDatabase
    private val _message: MutableLiveData<String> = MutableLiveData()
    val message: LiveData<String> = _message
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    suspend fun fetchAndLoadDogs() {
        repository.getAllDogs(
            onStart = { _loading.postValue(true) },
            onComplete = { _loading.postValue(false) },
            onError = { _message.postValue(it) }
        ).collect {
            _dogsDatabase.postValue(it)
        }
    }
}