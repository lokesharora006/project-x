package com.example.amphibians.data

import com.example.amphibians.model.AmphibiansModel
import com.example.amphibians.network.AmphibiansApiService

interface AmphibiansRepository {
    suspend fun getAmphibiansData(): List<AmphibiansModel>
}

class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
): AmphibiansRepository {
    override suspend fun getAmphibiansData(): List<AmphibiansModel> =
        amphibiansApiService.getAmphibiansData()
}