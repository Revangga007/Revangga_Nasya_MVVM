package com.revangga.revangga_nasya_mvvm.data.repository

import com.revangga.revangga_nasya_mvvm.data.local.DogsDao
import com.revangga.revangga_nasya_mvvm.data.model.Dog
import com.revangga.revangga_nasya_mvvm.data.network.ApiService
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val dogsDao: DogsDao,
    private val ioDispatcher: CoroutineDispatcher
) {

suspend fun getAllDogs(

    onStart: () -> Unit,
    onComplete: () -> Unit,
    onError: (String?) -> Unit
) = flow {
    val dog = dogsDao.getAllDogs()


        val response = apiService.getAllDogs()
        response.suspendOnSuccess {
            val dogConvert = data.map {
                Dog(null, it.url)
            }
            dogsDao.insertAllDogs(dogConvert)
            emit(dogConvert)
            emit(dog)
        }.onError {
            println(this.message())
            onError(this.message())
        }.onException {
            println(this.message())
            onError(message)
        }
}
    .onStart { onStart() }
    .onCompletion { onComplete() }
    .flowOn(ioDispatcher)

}
