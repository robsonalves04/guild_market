package com.example.guild_market.viewmodels

import android.app.Activity
import android.content.Context
import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guild_market.models.MarketProdutoModel
import com.example.guild_market.services.IMarketProdutoService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MarketProdutoViewModel(
    //== Variavel privada que possui o serviço de requisição da API
    private val _produtoService: IMarketProdutoService
) : ViewModel() {
    //== Variavel que contem os dados e podem ser alterados, caso requisitado
    val itemMock = MutableLiveData<List<MarketProdutoModel>>()
    //== Função que contem o retorno da lista de produtos
    fun sucessMock(model: List<MarketProdutoModel>) {
        itemMock.value = model
    }
    //==Função que apresenta sucesso ou falha do retorno da requisição
    fun produtoMock(context: Context) {
        viewModelScope.launch(Dispatchers.Main) {
            val callback = Callback<List<MarketProdutoModel>>(
                onSucesso = { model ->
                    sucessMock(model)
                },
            )
            _produtoService.obterProdutos(context, callback)
        }
    }
}

//== Função de Snackbar
fun MarketSnackbar(context: Context, message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    val snackbar = Snackbar.make((context as Activity).findViewById(android.R.id.content), message, duration)
    // Altera a cor de fundo da Snackbar
    snackbar.view.setBackgroundColor(Color.RED)
    snackbar.show()
}

//== Classe do callback com o tratamento da falha ou sucesso, que irá apresentar na tela
data class Callback<D>(
    val onSucesso: (res: D) -> Unit,
)