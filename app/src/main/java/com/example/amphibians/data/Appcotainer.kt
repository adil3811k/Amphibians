package com.example.amphibians.data

import com.example.amphibians.network.AmphibiansAPIService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface Appcotainer {
    val amphibiansDetailRepository:AmphibiansDetailRepository
}
class DefaultAppContainer:Appcotainer{
    private val BASE_URI ="https://android-kotlin-fun-mars-server.appspot.com/"
    private val retrofit =Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URI)
        .build()

    private val retrofitServise: AmphibiansAPIService by lazy {
        retrofit.create(AmphibiansAPIService::class.java)
    }
    override val amphibiansDetailRepository: AmphibiansDetailRepository by lazy {
        NetworkAmphibiansDetailRepository(retrofitServise)
    }

}