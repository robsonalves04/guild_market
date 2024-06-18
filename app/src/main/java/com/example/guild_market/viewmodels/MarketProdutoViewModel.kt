package com.example.guild_market.viewmodels

import android.app.Activity
import android.content.Context
import android.graphics.Color
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guild_market.mocks.produtosMock
import com.example.guild_market.models.MarketProdutoModel
import com.example.guild_market.services.produto_service.IMarketProdutoService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MarketProdutoViewModel(
    //== Variavel privada que possui o serviço de requisição da API
    private val _produtoService: IMarketProdutoService
) : ViewModel() {
    //== Variavel que contem os dados e podem ser alterados, caso requisitado
    val itemMock = MutableLiveData<List<MarketProdutoModel>>()

    private val _itemNovo = MutableLiveData<List<MarketProdutoModel>>()
    val itemNovo: LiveData<List<MarketProdutoModel>> = _itemNovo

    init {
        _itemNovo.value = produtosMock.toMutableList()
    }

    fun adicionarProduto(produtoModel: MarketProdutoModel) {
        val atualLista = _itemNovo.value?.toMutableList() ?: mutableListOf()
        atualLista.add(produtoModel)
        _itemNovo.value = atualLista
    }



    val incluirProduto = mutableStateOf("")
//
//
//    val itemAtual  = MutableLiveData<MarketProdutoModel>(null)
//
//    val itemNovo  = MutableLiveData<List<MarketProdutoModel>>(null)
//
//    fun adicionarProduto (produtoModel: MarketProdutoModel){
//        val listaAtualizada = itemNovo.value.orEmpty()
//            .toMutableList().apply { add(produtoModel) }
//        itemNovo.postValue(listaAtualizada)
//        itemNovo.value = listaAtualizada
//    }



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