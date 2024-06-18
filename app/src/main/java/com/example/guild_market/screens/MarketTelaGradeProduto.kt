package com.example.guild_market.screens

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.guild_market.components.MarketProdutoLista
import com.example.guild_market.viewmodels.MarketProdutoViewModel
import org.koin.android.ext.android.inject

class MarketTelaGradeProduto: AppCompatActivity()  {
    //== Variavel privada que recebe as informações contidas no ViewModel e trafega por secção autorizada
    private val _produtoViewModel: MarketProdutoViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //== Lista de Produtos Componetizada
            MarketProdutoLista(_produtoViewModel)
        }
    }
}