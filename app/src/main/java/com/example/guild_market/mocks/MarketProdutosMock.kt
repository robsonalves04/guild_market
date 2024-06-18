package com.example.guild_market.mocks

import com.example.guild_market.models.MarketProdutoModel

val produtosMock = listOf<MarketProdutoModel>(

    MarketProdutoModel(titulo = "Peixe", valor = "R$ 20,00", descricao = "peixe a milanesa"),
    MarketProdutoModel(titulo = "Carne", valor = "R$ 25,00", descricao = "carne assada"),
    MarketProdutoModel(titulo = "Frango", valor = "R$ 18,00", descricao = "frango grelhado"),
    MarketProdutoModel(titulo = "Camarão", valor = "R$ 30,00", descricao = "camarão empanado"),
    MarketProdutoModel(titulo = "Salada", valor = "R$ 15,00", descricao = "salada mista"),
    MarketProdutoModel(titulo = "Sopa", valor = "R$ 12,00", descricao = "sopa de legumes")

)