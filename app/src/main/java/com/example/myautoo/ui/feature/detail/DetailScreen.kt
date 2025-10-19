package com.example.myautoo.ui.feature.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import com.example.myautoo.data.model.CarModel

@Composable
fun DetailScreen(
   car: CarModel,
   onBack:()-> Unit,
   onFav:()-> Unit={},
   onBuyNow:()-> Unit={}
){
    val scroll= rememberScrollState()
    Box(){}
}