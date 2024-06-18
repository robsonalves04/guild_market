package com.example.guild_market.models
//== Modelo de requisição da API
data class MarketProdutoModel (
    val id: String? = null,
    var titulo: String? = null,
    val descricao: String? = null,
    val valor: String? = null
)