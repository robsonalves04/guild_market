package com.example.guild_market.services

import android.content.Context

interface IMarketProdutoService {

    suspend fun obterProdutos(
        context: Context
    )

}