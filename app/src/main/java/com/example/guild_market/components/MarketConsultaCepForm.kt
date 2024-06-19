package com.example.guild_market.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalMall
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guild_market.R
import com.example.guild_market.screens.MarketTelaConsultaCep
import com.example.guild_market.screens.MarketTelaGradeProduto
import com.example.guild_market.screens.MarketTelaInicial
import com.example.guild_market.viewmodels.MarketProdutoViewModel

@Composable
fun MarketConsultaCepForm(_produtoViewModel: MarketProdutoViewModel) {
    //==Variavel de contexto
    val context = LocalContext.current
    //== Variaveis de manipulação da requisição do CEP 60135645
    val andress = _produtoViewModel.andress.observeAsState()
    val digiteCep = remember { mutableStateOf("") }
    val paginaVazia = remember { mutableStateOf(0) }

    //== Variaveis de configuração da navbar
    val selectedIndex = remember { mutableStateOf(0) }
    val scaffoldState = rememberScaffoldState()

    //== NavBar posicionada de maneira fixa no rodapé da pagina.
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigation(backgroundColor = Color(0xFF0D5D52)) {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            Icons.Filled.Home,
                            contentDescription = "Home",
                            tint = Color(0xFFF5A822)
                        )
                    },
                    label = {
                        Text(
                            "Home",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    },
                    selected = selectedIndex.value == 0,
                    onClick = {
                        val intent = Intent(context, MarketTelaInicial::class.java)
                        context.startActivity(intent)
                        selectedIndex.value = 0
                    }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(
                            Icons.Filled.LocalMall,
                            contentDescription = "Lista de Produtos",
                            tint = Color(0xFFF5A822)
                        )
                    },
                    label = {
                        Text(
                            "Lista de Produtos",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    },
                    selected = selectedIndex.value == 1,
                    onClick = {
                        val intent = Intent(context, MarketTelaGradeProduto::class.java)
                        context.startActivity(intent)
                        selectedIndex.value = 1
                    }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "Consulta CEP",
                            tint = Color(0xFFF5A822)
                        )
                    },
                    label = {
                        Text(
                            "Consulta CEP",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    },
                    selected = selectedIndex.value == 0,
                    onClick = {
                        val intent = Intent(context, MarketTelaConsultaCep::class.java)
                        context.startActivity(intent)
                        selectedIndex.value = 0
                    }
                )
            }
        },
        // == Conteudo da pagina, acima da NavBar
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                Box {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .padding(top = 15.dp, start = 15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Busque o seu CEP",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                //== Secção que apresenta o resultado após a requisição da API
                Column {
                    if (paginaVazia.value == 1) {
                        andress.value?.let { it1 ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                                    .padding(10.dp),
                            ) {
                                Column(
                                    Modifier
                                        .padding(10.dp)
                                        .fillMaxWidth(),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Row(
                                        Modifier
                                            .padding(5.dp)
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "${it1.logradouro ?: "Esse CEP não existe"}",
                                            fontSize = 22.sp,
                                            color = Color.Black
                                        )
                                    }
                                    Row(
                                        Modifier
                                            .padding(5.dp)
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Bairro: ${it1.bairro ?: ""}",
                                            fontSize = 22.sp,
                                            color = Color.Black
                                        )
                                        Text(
                                            text = "Cep: ${it1.cep ?: ""}",
                                            fontSize = 22.sp,
                                            color = Color.Black
                                        )
                                    }
                                    Row(
                                        Modifier
                                            .padding(5.dp)
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Cidade: ${it1.localidade ?: ""}",
                                            fontSize = 22.sp,
                                            color = Color.Black
                                        )
                                        Text(
                                            text = "Estado: ${it1.uf ?: ""}",
                                            fontSize = 22.sp,
                                            color = Color.Black
                                        )
                                    }
                                }
                            }
                        }
                    } else {
                        //== Condição caso campo de busca esteja vazio
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.no_results),
                                contentDescription =
                                "Imagem de sem resultado",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(200.dp)
                                    .width(200.dp)
                                    .clip(RoundedCornerShape(12.dp))
                            )
                            Text(
                                text = "Nenhum cep foi digitado",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        //== Input TextFiel para inserir o valor desejado
                        MarketTextField(
                            widthFloat = 0.5f,
                            textAlign = TextAlign.Center,
                            maxLength = 8,
                            refValue = digiteCep,
                            onValueChange = {
                                digiteCep.value = it
                                if (it.isEmpty()) {
                                    paginaVazia.value = 0
                                }
                            },
                            placeholder = "Digite o Cep",
                            tipoText = KeyboardType.Number
                        )
                        Box {
                            // == Botão de busca
                            Button(
                                onClick = {
                                    val result = _produtoViewModel.mostrarCep(context, digiteCep.value)
                                    paginaVazia.value = if (result != null) 1 else 0
                                },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(0xFFF5A822)
                                ),
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                                    .height(50.dp)
                            ) {
                                Text(text = "Buscar", fontSize = 22.sp)
                            }
                        }
                    }
                }
            }
        }
    )
}