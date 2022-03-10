package com.revangga.revangga_nasya_mvvm.data.network

import com.revangga.revangga_nasya_mvvm.data.model.DogsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("images/search")
    suspend fun getAllDogs(): Response<DogsResponse>
}