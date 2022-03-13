package com.revangga.revangga_nasya_mvvm.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revangga.revangga_nasya_mvvm.data.model.Dog
import com.revangga.revangga_nasya_mvvm.data.model.DogsData
import com.revangga.revangga_nasya_mvvm.data.repository.MainRepository

class DogViewModel(private val repository: MainRepository) : ViewModel() {

    var view: DogView? = null

    private val _dogsDatabase = MutableLiveData<List<Dog>>()
    val dogsDatabase: LiveData<List<Dog>> = _dogsDatabase

    suspend fun fetchAndaLoadDogs() {
        view?.showLoading()
        val result = repository.getAllDogs()
        result?.let {
            val dogs = ArrayList<DogsData>()
            it.forEach { response ->
                dogs.add(
                    DogsData(
                        response.breed,
                        response.id,
                        response.url,
                        response.width,
                        response.height
                    )
                )
            }
            repository.saveAllDogs(dogs)
        }

        val loadFromDatabase = repository.loadAllDogs()
        Log.d("Local", loadFromDatabase.toString())
        _dogsDatabase.postValue(loadFromDatabase)
        view?.hideLoading()
    }
}