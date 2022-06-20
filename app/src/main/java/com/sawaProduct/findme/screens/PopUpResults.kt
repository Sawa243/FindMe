package com.sawaProduct.findme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sawaProduct.findme.R
import com.sawaProduct.findme.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopUpResults(score: Int, blacking: Boolean, popUpOk: () -> Unit) {

    BoxWithConstraints (modifier = Modifier
        .fillMaxSize()
        .clickable { }, contentAlignment = Alignment.Center) {
        Card(
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (blacking) {
                    Color.DarkGray
                } else {
                    Color.White
                }
            )

        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 10.dp)
                    .fillMaxWidth(0.7f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.results),
                    contentDescription = "",
                    modifier = Modifier.size(35.dp)
                )
                Text(
                    text = stringResource(id = R.string.result_uppercase),
                    style = TextStyle(blacking),
                    fontSize = 30.sp,
                    modifier = Modifier.padding(top = 10.dp, bottom = 5.dp)
                )
                Text(
                    text = score.toString(),
                    style = TextStyle(blacking),
                    modifier = Modifier.padding(bottom = 30.dp)
                )
                Button(
                    onClick = { popUpOk.invoke() }, colors = ButtonDefaults.buttonColors(
                        containerColor = if (blacking) {
                            Color.DarkGray
                        } else {
                            Color.White
                        }
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.ok_uppercase),
                        style = TextStyle(blacking),
                        modifier = Modifier.padding(
                            start = 10.dp,
                            end = 10.dp,
                            top = 5.dp,
                            bottom = 5.dp
                        )
                    )
                }
            }
        }
    }

}