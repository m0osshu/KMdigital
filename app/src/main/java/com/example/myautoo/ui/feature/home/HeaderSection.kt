package com.example.myautoo.ui.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myautoo.R
import com.example.myautoo.ui.theme.MyAutooTheme

@Composable
fun HeaderSection(username: String = "test", onBellClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logokmd),
            contentDescription = "Logo",
            modifier = Modifier.size(70.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderSectionPreview() {
    MyAutooTheme {
        HeaderSection(username = "test") {
            println("Bell icon clicked")
        }
    }
}