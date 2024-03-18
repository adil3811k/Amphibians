package com.example.amphibians.network

import com.example.amphibians.modul.Amphibian
import retrofit2.http.GET

interface AmphibiansAPIService {
    @GET("amphibians")
    suspend fun getDetail():List<Amphibian>
}