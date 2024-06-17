package com.example.guild_market.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.guild_market.components.MarketOnboardForm
import com.example.guild_market.components.MarketProdutoLista

class MarketGradeProduto: AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarketProdutoLista()
        }
    }
}