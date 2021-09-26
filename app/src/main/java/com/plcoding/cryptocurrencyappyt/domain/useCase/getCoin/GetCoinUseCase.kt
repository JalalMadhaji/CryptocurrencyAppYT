package com.plcoding.cryptocurrencyappyt.domain.useCase.getCoin

import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoinDetail
import com.plcoding.cryptocurrencyappyt.domain.model.CoinDetail
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import com.plcoding.cryptocurrencyappyt.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val repository: CoinRepository) {
    operator fun invoke(coinId : String) : Flow<Resource<CoinDetail>> = flow{
        try {
            emit(Resource.Loading())
            val coinDetail = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coinDetail))
        }catch (e : HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected HTTP error occurred"))
        }catch (e : IOException){
            emit(Resource.Error(e.localizedMessage ?: "Could not reach server, check your connection"))
        }
    }
}
