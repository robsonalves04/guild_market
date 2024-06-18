package com.example.guild_market.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guild_market.models.MarketProdutoModel
import com.example.guild_market.services.IMarketProdutoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MarketProdutoViewModel(
    private val _produtoService: IMarketProdutoService
) : ViewModel() {
    var itemMock = MutableLiveData<List<MarketProdutoModel>>()

    fun sucessMock(model: List<MarketProdutoModel>) {
        itemMock.value = model
    }

    fun produtoMock(context: Context) {
        viewModelScope.launch(Dispatchers.Main) {
            val callback = Callback<List<MarketProdutoModel>>(
                onSucesso = { model ->
                    sucessMock(model)

                }, falha = {}
            )
            _produtoService.obterProdutos(context, callback)

        }

    }

}

data class Callback<D>(
    val onSucesso: (res: D) -> Unit,
    val falha: (problema: String) -> Unit
)