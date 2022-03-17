package com.revangga.revangga_nasya_mvvm.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revangga.revangga_nasya_mvvm.data.model.Dog
import com.revangga.revangga_nasya_mvvm.data.model.DogsData
import com.revangga.revangga_nasya_mvvm.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val repository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher
    ) : ViewModel() {

    private val _dogsDatabase = MutableLiveData<List<Dog>>()
    val dogsDatabase: LiveData<List<Dog>> = _dogsDatabase
    private val _message: MutableLiveData<String> = MutableLiveData()
    val message: LiveData<String> = _message
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    suspend fun fetchAndaLoadDogs() {
        viewModelScope.launch(ioDispatcher) {
     repository.getAllDogs(
         onStart = { _loading.postValue(true) },
         onComplete = { _loading.postValue(false) },
         onError = { _message.postValue(it) }     )

        }


}