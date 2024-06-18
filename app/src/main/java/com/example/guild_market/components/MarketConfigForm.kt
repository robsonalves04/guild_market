package com.example.guild_market.components

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalMall
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guild_market.models.MarketProdutoModel
import com.example.guild_market.screens.MarketTelaGradeProduto
import com.example.guild_market.screens.MarketTelaInicial
import com.example.guild_market.viewmodels.MarketProdutoViewModel

@Composable
fun MarketConfigForm(_produtoViewModel: MarketProdutoViewModel) {
    //== Variaveis de configuração da navbar
    val selectedIndex = remember { mutableStateOf(0) }
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current


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
//                        coroutineScope.launch {
//                            scaffoldState.snackbarHostState.showSnackbar("Home")
//                        }
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
            }
        },
        // == Conteudo da pagina, acima da NavBar
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(Modifier.fillMaxSize()) {
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
                                text = "Inclua novo produto aqui",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    MarketTextField(refValue = _produtoViewModel.incluirProduto,
                        onValueChange = { x ->
                            _produtoViewModel.incluirProduto.value = x
                        },placeholder = "Digite o produto", tipoText =  KeyboardType.Text)
//                    MarketTextField(placeholder = "Digite a descrição", KeyboardType.Text)
//                    MarketTextField(placeholder = "Digite o valor", KeyboardType.Decimal)

                    Box {
                        //== Botão de acesso ao aplicativo
                        Button (
                            onClick = {
                                _produtoViewModel.adicionarProduto(produtoModel = MarketProdutoModel())
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFFF5A822)
                            ),
                            modifier = Modifier.padding(16.dp).fillMaxWidth().height(50.dp)
                        ) {
                            Text(text = "Incluir", fontSize = 22.sp)
                        }
                    }
                }
            }
        }
    )
}