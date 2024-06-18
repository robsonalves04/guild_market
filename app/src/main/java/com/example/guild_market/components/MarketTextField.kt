package com.example.guild_market.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MarketTextField(
    placeholder: String ?= "",
    tipoText: KeyboardType? = KeyboardType.Text,
) {
    val refValue = remember { mutableStateOf("") }
    val maxLength: Int = 20
    val onValueChange: (String) -> Unit = { newValue ->
        refValue.value = newValue
    }

    Column(Modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {

            BasicTextField(
                value = refValue.value,
                onValueChange = { x ->
                    if (x.length <= maxLength) {
                        refValue.value = x
                        onValueChange.invoke(x)
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = tipoText!!,
                    imeAction = ImeAction.Next,
                    autoCorrect = true
                ),
                textStyle = TextStyle(
                    textAlign = TextAlign.Left,
                    fontSize = 22.sp,
                    color = Color.Black,
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            )
            if (refValue.value.isEmpty()) {
                Text(
                    text = placeholder!!,
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 10.dp)
                )
            }
        }
    }
}