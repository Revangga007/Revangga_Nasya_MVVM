package com.revangga.revangga_nasya_mvvm.ui

import androidx.lifecycle.ViewModel
import com.revangga.revangga_nasya_mvvm.data.repository.MainRepository

class DogViewModel(private val repository: MainRepository) : ViewModel()  {

    fun getALLDogs() = repository.getALLDogs()
}