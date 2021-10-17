package com.manishbhati.data

import com.manishbhati.data.data.DataResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("?results=10")
    suspend fun getData(): Response<DataResponse>
}

