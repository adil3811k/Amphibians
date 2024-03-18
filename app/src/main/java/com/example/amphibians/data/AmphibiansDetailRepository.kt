package com.example.amphibians.data

import com.example.amphibians.modul.Amphibian
import com.example.amphibians.network.AmphibiansAPIService


interface AmphibiansDetailRepository {
    suspend fun getAmphibiansDetail() : List<Amphibian>
}
class NetworkAmphibiansDetailRepository(
    private val amphibiansAPIService: AmphibiansAPIService
):AmphibiansDetailRepository{
    override suspend fun getAmphibiansDetail(): List<Amphibian> = amphibiansAPIService.getDetail()
}