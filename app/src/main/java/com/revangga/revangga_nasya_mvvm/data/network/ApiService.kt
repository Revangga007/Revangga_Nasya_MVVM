package com.revangga.revangga_nasya_mvvm.data.network

import com.revangga.revangga_nasya_mvvm.data.model.DogsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("images/search")
    suspend fun getAllDogs(
        @Query("size") size: String? = null,
        @Query("mime_types") mimeType: String? = null,
        @Query("format") format: String? = null,
        @Query("has_breeds") hasBreeds: Boolean? = null,
        @Query("order") order: String? = null,
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null,
        @Query("key") key: String? = null

    ): Response<DogsResponse>
}