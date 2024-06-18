package com.example.guild_market.components

import android.content.Intent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guild_market.R
import com.example.guild_market.models.MarketProdutoModel
import com.example.guild_market.screens.MarketGradeProduto
import com.example.guild_market.screens.MarketTelaInicial
import com.example.guild_market.viewmodels.MarketProdutoViewModel
import kotlinx.coroutines.launch

@Composable
fun MarketProdutoLista(
    _produtoViewModel: MarketProdutoViewModel
) {

    val itemMock = _produtoViewModel.itemMock.observeAsState()
    val context = LocalContext.current
    val selectedIndex = remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(Unit) {
        _produtoViewModel.produtoMock(context)
    }

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
                    label = { Text("Home", fontSize = 12.sp, fontWeight = FontWeight.Bold) },
                    selected = selectedIndex.value == 0,
                    onClick = {
                        val intent = Intent(context, MarketTelaInicial::class.java)
                        context.startActivity(intent)
                        selectedIndex.value = 0
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Home")
                        }
                    }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = "Settings",
                            tint = Color(0xFFF5A822)
                        )
                    },
                    label = {
                        Text(
                            "Configuração",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    selected = selectedIndex.value == 1,
                    onClick = {
                        selectedIndex.value = 1
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Configuração")
                        }
                    }
                )
            }
        },
        // == Conteudo da pagina
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
                                text = "Lista de Produtos",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                itemMock.value.let { it ->
                    it?.map { it3 ->
                        val listState = rememberLazyListState()
                        val chunkedList =  it.chunked(3)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(10.dp),
                        ) {
                            LazyColumn(state = listState) {
                                val mods = Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth()
                                itemsIndexed(chunkedList) { index, rowItems ->

                                    Column(modifier = Modifier.fillMaxHeight()) {
                                        Row(
                                            modifier = mods,
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            rowItems.forEach { item ->

                                                Box(modifier = Modifier.padding(top = 8.dp)) {
                                                    Column(
                                                        Modifier
                                                            .width(100.dp)
                                                            .height(180.dp)
                                                            .clip(RoundedCornerShape(12.dp)),
                                                        horizontalAlignment = Alignment.CenterHorizontally
                                                    ) {
                                                        Text(
                                                            text = it3.titulo!!,

                                                        )
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
                                                        Text(
                                                            text = it3.descricao!!, textAlign = TextAlign.Center, fontSize = 5.sp
                                                        )
                                                        Text(
                                                            text = it3.valor!!,textAlign = TextAlign.Start, fontSize = 5.sp, fontWeight = FontWeight.SemiBold
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
    )
}






