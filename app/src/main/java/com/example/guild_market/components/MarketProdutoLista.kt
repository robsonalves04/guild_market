package com.example.guild_market.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guild_market.R
import com.example.guild_market.screens.MarketTelaConfigProduto
import com.example.guild_market.screens.MarketTelaConsultaCep
import com.example.guild_market.screens.MarketTelaInicial
import com.example.guild_market.viewmodels.MarketProdutoViewModel

@Composable
fun MarketProdutoLista(
    //== trafego de informações pelo viewModel
    _produtoViewModel: MarketProdutoViewModel
) {
    //== Variaveis de inicialização e consumo da API
    val itemMock = _produtoViewModel.itemMock.observeAsState()
    val context = LocalContext.current
    //== Variaveis de configuração da navbar
    val selectedIndex = remember { mutableStateOf(0) }
    val scaffoldState = rememberScaffoldState()
    //== efeito para exibir na tela a lista de produtos
    LaunchedEffect(Unit) {
        _produtoViewModel.produtoMock(context)
    }
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
                        Text("Home", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White) },
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
                            Icons.Filled.Settings,
                            contentDescription = "Configuração",
                            tint = Color(0xFFF5A822)
                        )
                    },
                    label = {
                        Text(
                            "Configuração",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    },
                    selected = selectedIndex.value == 1,
                    onClick = {
                        val intent = Intent(context, MarketTelaConfigProduto::class.java)
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
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF0D5D52).copy(alpha = 0.5f),
                                Color(0xFF00BFA5).copy(alpha = 0.2f)
                            )
                        )
                    )
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
                                text = "Lista de Produtos",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                //== Mapeamento dos Produtos
                itemMock.value.let { produtoLista ->
                    produtoLista?.forEach { produto ->
                        val chunkedList = produtoLista.chunked(3)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(10.dp),
                        ) {
                            LazyColumn {
                                val mods = Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth()
                                chunkedList.forEach { rowItems ->
                                    item {
                                        Column(modifier = Modifier.fillMaxHeight()) {
                                            Row(
                                                modifier = mods,
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                rowItems.forEach { item ->
                                                    //== Secção de Cards de produtos
                                                    Box(modifier = Modifier.padding(top = 8.dp)) {
                                                        Column(
                                                            Modifier
                                                                .width(100.dp)
                                                                .height(180.dp)
                                                                .clip(RoundedCornerShape(12.dp)),
                                                            horizontalAlignment = Alignment.CenterHorizontally
                                                        ) {
                                                            //== Titulo do Produto
                                                            Text(
                                                                text = item.titulo!!,
                                                                modifier = Modifier.padding(bottom = 4.dp)
                                                            )
                                                            //== Imagem do Produto
                                                            Image(
                                                                painter = painterResource(id = R.drawable.sem_produto),
                                                                contentDescription =
                                                                "Imagem carregada da internet",
                                                                contentScale = ContentScale.Crop,
                                                                modifier = Modifier
                                                                    .height(100.dp)
                                                                    .width(100.dp)
                                                                    .clip(RoundedCornerShape(12.dp))
                                                            )
                                                            //== Descrição do Produto
                                                            Text(
                                                                text = item.descricao!!,
                                                                textAlign = TextAlign.Justify,
                                                                fontSize = 10.sp,
                                                                modifier = Modifier.padding(
                                                                    top = 8.dp,
                                                                    bottom = 3.dp
                                                                )
                                                            )
                                                            //== Valor do Produto
                                                            Text(
                                                                text = item.valor!!,
                                                                textAlign = TextAlign.Start,
                                                                fontSize = 12.sp,
                                                                fontWeight = FontWeight.SemiBold
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}