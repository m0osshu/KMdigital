package com.example.myautoo.ui.feature.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.myautoo.R
import com.example.myautoo.data.model.CarModel

@Composable
fun DetailScreen(
   car: CarModel,
   onBack:()-> Unit,
   onFav:()-> Unit={},
   onBuyNow:()-> Unit={}
){
    val scroll= rememberScrollState()
    Box(Modifier.fillMaxWidth()
        .background(colorResource(R.color.white))){
        DetailHeader(car.picUrl,onBack,onBack)
        Column (Modifier
            .fillMaxWidth()
            .padding(top = 450.dp)
            .verticalScroll(state = scroll)
        ){
            DetailInfo(car.title, car.rating,car.description)
            DetailSpecs(car.totalCapacity,car.highestSpeed,car.engineOutput)
            DetailPriceBar(car.price, onBuyNow)
            Spacer(Modifier.height(24.dp))
        }
    }
}




