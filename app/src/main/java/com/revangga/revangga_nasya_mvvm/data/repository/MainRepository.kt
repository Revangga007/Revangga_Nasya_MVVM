package com.revangga.revangga_nasya_mvvm.data.repository

import com.revangga.revangga_nasya_mvvm.data.local.DogsDao
import com.revangga.revangga_nasya_mvvm.data.model.Dog
import com.revangga.revangga_nasya_mvvm.data.model.DogsData
import com.revangga.revangga_nasya_mvvm.data.network.ApiService
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
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

    if (dog.isEmpty()) {
        val response = apiService.getAllDogs()
        response.suspendOnSuccess {
            val dogConvert = data.dogsData.map {
                Dog(it.id.toLong(), it.url)
            }
            dogsDao.insertAllDogs(dogConvert)
            emit(dogConvert)
        }.onError {
            onError(this.message())
        }.onException {
            onError(message)
        }
    } else {
        emit(dogsDao.getAllDogs())
    }
}
    .onStart { onStart() }
    .onCompletion { onComplete() }
    .flowOn(ioDispatcher)

}
