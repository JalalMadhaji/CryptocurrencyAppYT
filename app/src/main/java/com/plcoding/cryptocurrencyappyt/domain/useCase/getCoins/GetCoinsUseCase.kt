package com.plcoding.cryptocurrencyappyt.domain.useCase.getCoins

import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoin
import com.plcoding.cryptocurrencyappyt.domain.model.Coin
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import com.plcoding.cryptocurrencyappyt.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinRepository) {
    operator fun invoke() : Flow<Resource<List<Coin>>> = flow{
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        }catch (e : HttpException){
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected HTTP error occurred"))
        }catch (e : IOException){
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "Could not reach server, check your connection"))
        }
    }
}
