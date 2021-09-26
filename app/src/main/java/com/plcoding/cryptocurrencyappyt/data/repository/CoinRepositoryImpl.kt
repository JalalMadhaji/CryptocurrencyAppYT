package com.plcoding.cryptocurrencyappyt.data.repository

import com.plcoding.cryptocurrencyappyt.data.remote.CoinApiService
import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDetailDto
import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDto
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val apiService: CoinApiService
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return apiService.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return apiService.getCoinById(coinId)
    }
}
