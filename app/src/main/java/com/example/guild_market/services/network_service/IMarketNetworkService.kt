package com.example.guild_market.services.network_service

import com.example.guild_market.models.MarketAndressModel
import retrofit2.http.GET
import retrofit2.http.Path

interface IMarketNetworkService {
    //== GET do protocolo HTTP
    @GET("{cep}/json/")
    suspend fun findAndress(@Path("cep") cep: String): MarketAndressModel
}




