package com.example.guild_market.services.produto_service

import android.content.Context
import com.example.guild_market.mocks.produtosMock
import com.example.guild_market.models.MarketProdutoModel
import com.example.guild_market.viewmodels.Callback

class MarketProdutoService : IMarketProdutoService {

    //== Serviço que é injetado, para que possa ser usado apos tratado
    override suspend fun obterProdutos(
        context: Context,
        opcao: Callback<List<MarketProdutoModel>>
    ) {
        //== requisição da API, basicamente nesse lugar ficará a baseURL
        //== getAsync, path: "https://fakestoreapi.com.",
        opcao.onSucesso(produtosMock)
    }
}