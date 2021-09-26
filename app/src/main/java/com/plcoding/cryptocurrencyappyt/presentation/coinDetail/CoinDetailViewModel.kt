package com.plcoding.cryptocurrencyappyt.presentation.coinDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.domain.useCase.getCoin.GetCoinUseCase
import com.plcoding.cryptocurrencyappyt.util.Constants
import com.plcoding.cryptocurrencyappyt.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _state = mutableStateOf(CoinDetailState())
    val state : State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.COIN_ID)?.let {
            getCoinDetail(it)
        }
    }

    private fun getCoinDetail(coinId : String){
        getCoinUseCase(coinId).onEach {
            when(it){
                is Resource.Success -> {
                    _state.value = CoinDetailState(coinDetail = it.data)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(error = it.message!! ?: "Error happened")
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
