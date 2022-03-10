package com.revangga.revangga_nasya_mvvm.data.repository

import android.content.Context
import com.revangga.revangga_nasya_mvvm.data.local.DogsDatabase
import com.revangga.revangga_nasya_mvvm.data.model.Dog
import com.revangga.revangga_nasya_mvvm.data.model.DogsData
import com.revangga.revangga_nasya_mvvm.data.model.DogsResponse
import com.revangga.revangga_nasya_mvvm.data.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(context: Context) {
    private val retrofit = ApiClient.instance
    private val database = DogsDatabase.instance(context)

    suspend fun getAllDogs(): DogsResponse? {
        var result: DogsResponse?

        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            result = try {
                retrofit.getAllDogs().body()
            } catch (e: Exception) {
                null
            }
        }
        return result
    }

    suspend fun saveAllDogs(dogsData: List<DogsData>): Boolean {
        var successInsert: Boolean
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            val dogs = dogsData.map {
                Dog(it.id.toLong(), it.url)
            }
            successInsert = try {
                database.dog().insertAllDogs(dogs)
                true
            } catch (e: Exception) {
                false
            }
        }
        return successInsert
    }

    suspend fun loadAllDogs(): List<Dog> {
        var result: List<Dog>
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            result = database.dog().getAllDogs()
        }
        return result
    }
}