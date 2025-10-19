package com.example.myautoo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.myautoo.navigation.AppNavGraph
import com.example.myautoo.ui.feature.home.MainScreen
import com.example.myautoo.ui.viewModel.CarViewModel
import com.example.myautoo.ui.viewModel.CategoryViewModel

class MainActivity: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            AppNavGraph()
        }
    }
}