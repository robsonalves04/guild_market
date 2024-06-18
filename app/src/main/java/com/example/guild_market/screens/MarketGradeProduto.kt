package com.example.guild_market.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.appcompat.app.AppCompatActivity

import com.example.guild_market.components.MarketOnboardForm
import com.example.guild_market.components.MarketProdutoLista
import com.example.guild_market.mocks.produtosMock
import com.example.guild_market.viewmodels.MarketProdutoViewModel
import org.koin.android.ext.android.inject
class MarketGradeProduto: AppCompatActivity()  {

    private val _produtoViewModel: MarketProdutoViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarketProdutoLista(_produtoViewModel)
        }
    }
}