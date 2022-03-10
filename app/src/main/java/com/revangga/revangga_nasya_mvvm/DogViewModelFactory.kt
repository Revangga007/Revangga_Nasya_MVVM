package com.revangga.revangga_nasya_mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.revangga.revangga_nasya_mvvm.data.repository.MainRepository
import java.lang.Exception
import java.lang.IllegalArgumentException

class DogViewModelFactory (
    private val repository: MainRepository
    ): ViewModelProvider.NewInstanceFactory() {
        override fun <T: ViewModel> create(modelClass: Class<T>): T {
            try {
                return modelClass.getDeclaredConstructor(
                    MainRepository::class.java
                ).newInstance(repository) as T
            } catch (e: Exception) {
                throw IllegalArgumentException("Unknown ViewModel class "+ modelClass.name)
            }
        }
    }