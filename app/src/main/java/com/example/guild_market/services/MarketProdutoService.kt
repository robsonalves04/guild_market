package com.example.guild_market.services

import android.content.Context
import com.example.guild_market.mocks.produtosMock

class MarketProdutoService : IMarketProdutoService {

    override suspend fun obterProdutos(
        context: Context

    ) {
        produtosMock
    }
}