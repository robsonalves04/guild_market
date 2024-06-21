package com.example.guild_market.viewmodels

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guild_market.components.toastSnackbar
import com.example.guild_market.mocks.produtosMock
import com.example.guild_market.models.MarketAndressModel
import com.example.guild_market.models.MarketProdutoModel
import com.example.guild_market.services.network_service.IMarketNetworkService
import com.example.guild_market.services.produto_service.IMarketProdutoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class MarketProdutoViewModel(
    //== Variavel privada que possui o serviço de requisição da API
    private val _produtoService: IMarketProdutoService,
    private val _networkService: IMarketNetworkService
) : ViewModel() {
    //== Variavel que contem os dados e podem ser alterados, caso requisitado
    val itemMock = MutableLiveData<List<MarketProdutoModel>>()

    //== Variavel que os dados mutaveis
    val andress = MutableLiveData<MarketAndressModel>()


    //== Variaveis de inclusão e manipulação de novos produtos
    val _itemNovo = MutableLiveData<List<MarketProdutoModel>>()
    val incluirProduto = mutableStateOf("")
    val incluirDescrição = mutableStateOf("")
    val incluirValor = mutableStateOf("")

    init {
        _itemNovo.value = produtosMock.toMutableList()
    }

    //== Função que obter e mostra o cep
    fun mostrarCep(context: Context, cep: String) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                //== variavel que recebe o valor da API e set o valor na MutableLiveData
                val result = _networkService.findAndress(cep)
                andress.value = result
                //== Tratamento do possiveis erro que vem da API, exceto se o cliente digitar errado
            } catch (e: HttpException) {
                when (e.code()) {
                    400 -> toastSnackbar(
                        context,
                        "CEP inválido. Verifique o número e tente novamente."
                    )

                    404 -> toastSnackbar(context, "É preciso digital algum CEP.")
                    else -> toastSnackbar(context, "Erro inesperado: ${e.message}")
                }
            } catch (e: IOException) {
                toastSnackbar(context, "Erro de rede. Verifique sua conexão com a internet.")
            } catch (e: Exception) {
                toastSnackbar(context, "Erro inesperado: ${e.message}")
            }
        }
    }

    //==  Essa função adiciona e trata possivel erros de inclusao de produto
    fun adicionarProduto(context: Context, produtoModel: MarketProdutoModel) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val atualLista = _itemNovo.value?.toMutableList() ?: mutableListOf()
                atualLista.add(produtoModel)
                _itemNovo.value = atualLista
                toastSnackbar(context, "Esse produto nao pode ser inserido.")
            } catch (e: IOException) {
                toastSnackbar(context, "Erro de conexão. Verifique sua internet.")
            }
        }
    }

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

//== Classe do callback com o tratamento da falha ou sucesso, que irá apresentar na tela
data class Callback<D>(
    val onSucesso: (res: D) -> Unit,
)