package com.example.guild_market.screens

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.guild_market.components.MarketConfigForm

class MarketTelaConfigProduto: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //== Formulario de Configuração do Produto
            MarketConfigForm()
        }
    }
}