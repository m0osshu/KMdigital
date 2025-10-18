package com.example.myautoo.ui.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myautoo.R
import com.example.myautoo.ui.theme.MyAutooTheme

@Composable
fun HeaderSection(username: String = "test", onBellClick: () -> Unit = {}) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        val (profilePic, nameColumn, bellIcon) = createRefs()

        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(55.dp)
                .constrainAs(profilePic) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(anchor = parent.bottom)
                }
        )
        Column (modifier = Modifier.constrainAs(nameColumn){
            start.linkTo(anchor = profilePic.end, margin = 8.dp)
            top.linkTo(anchor = parent.top)
            bottom.linkTo(anchor = parent.bottom)
        }){
            Text(text = "Text", fontSize = 14.sp )
            Text(text = username, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
        Image(painter = painterResource(R.drawable.bell),
            contentDescription = null,
            modifier = Modifier
                .clickable{onBellClick()}
                .constrainAs(bellIcon){
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)

                })

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