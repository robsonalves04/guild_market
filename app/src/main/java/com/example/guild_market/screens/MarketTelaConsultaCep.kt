package com.example.guild_market.screens

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.guild_market.components.MarketConsultaCepForm
import com.example.guild_market.viewmodels.MarketProdutoViewModel
import org.koin.android.ext.android.inject

class MarketTelaConsultaCep : AppCompatActivity()  {

    private val _produtoViewModel: MarketProdutoViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //== Formulario de Tela Consulta CEP
           MarketConsultaCepForm(_produtoViewModel)
        }
    }
}