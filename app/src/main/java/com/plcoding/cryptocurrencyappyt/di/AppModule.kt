package com.plcoding.cryptocurrencyappyt.di

import com.plcoding.cryptocurrencyappyt.data.remote.CoinApiService
import com.plcoding.cryptocurrencyappyt.data.repository.CoinRepositoryImpl
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import com.plcoding.cryptocurrencyappyt.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCoinApiService() : CoinApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideCoinRepository(apiService: CoinApiService) : CoinRepository {
        return CoinRepositoryImpl(apiService)
    }
}
