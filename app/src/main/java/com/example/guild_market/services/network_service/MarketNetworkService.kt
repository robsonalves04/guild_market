package com.example.guild_market.services.network_service

import com.example.guild_market.models.MarketAndressModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MarketNetworkService : IMarketNetworkService {
    private val apiService: IMarketNetworkService
    init {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
        //== Base URL da requisição
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://viacep.com.br/ws/")
            .client(client)
            .build()

        apiService = retrofit.create(IMarketNetworkService::class.java)
    }
    //== Retorno da requisição
    override suspend fun findAndress(cep: String): MarketAndressModel {
        return apiService.findAndress(cep)
    }
}