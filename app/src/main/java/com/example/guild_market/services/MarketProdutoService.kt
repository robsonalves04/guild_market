package com.example.guild_market.services

import android.content.Context
import com.example.guild_market.mocks.produtosMock
import com.example.guild_market.models.MarketProdutoModel
import com.example.guild_market.viewmodels.Callback

class MarketProdutoService : IMarketProdutoService {

    override suspend fun obterProdutos(
        context: Context,
        opcao: Callback<List<MarketProdutoModel>>

    ) {
        opcao.onSucesso(produtosMock)

    }
}