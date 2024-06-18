package com.example.guild_market.services.API

import com.example.guild_market.models.MarketAndressModel
import retrofit2.http.GET
import retrofit2.http.Path

interface IMarketNetworkService {
//    https://viacep.com.br/ws/
    @GET("{cep}/json/")
   suspend fun findAndress(@Path("cep") cep: String): MarketAndressModel {


    }
}