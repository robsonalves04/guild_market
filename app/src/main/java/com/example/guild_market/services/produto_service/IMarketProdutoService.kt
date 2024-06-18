package com.example.guild_market.services.produto_service

import android.content.Context
import com.example.guild_market.models.MarketProdutoModel
import com.example.guild_market.viewmodels.Callback

interface IMarketProdutoService {
    //== Serviço que obtem o produto apos o tratamento da API
    suspend fun obterProdutos(
        context: Context,
        opcao: Callback<List<MarketProdutoModel>>
    )
}