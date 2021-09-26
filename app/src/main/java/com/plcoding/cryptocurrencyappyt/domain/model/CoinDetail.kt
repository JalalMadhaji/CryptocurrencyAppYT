package com.plcoding.cryptocurrencyappyt.domain.model

import com.plcoding.cryptocurrencyappyt.data.remote.dto.*

data class CoinDetail (
    val description: String,
    val coinId: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val tags: List<String>,
    val team: List<Team>
    )


