package com.sawaProduct.findme.screens

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sawaProduct.findme.R
import com.sawaProduct.findme.TextStyle
import com.sawaProduct.findme.ui.theme.Cyan
import com.sawaProduct.findme.ui.theme.Pink80
import kotlin.system.exitProcess

@Composable
fun MainScreen(modifier: Modifier, background: Color, navController: NavHostController) {

    val view = LocalView.current
    (view.context as Activity).window.statusBarColor = background.toArgb()
    (view.context as Activity).window.navigationBarColor = Color.Green.toArgb()

    val blacking by remember {
        mutableStateOf(background == Color.Black)
    }
    Box(
        modifier = modifier
            .background(background)
            .fillMaxSize()
    ) {
        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = modifier.padding(top = 40.dp),
                text = stringResource(id = R.string.app_name),
                color = colorResource(id = R.color.Tan),
                fontSize = 40.sp,
                lineHeight = 18.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight(700),
                letterSpacing = 0.1.sp
            )
            Box(
                modifier = modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth(), contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = { navController.navigate("gameScreen") },
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(),
                    modifier = modifier.width(250.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .background(Brush.horizontalGradient(colors = listOf(Pink80, Cyan)))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .width(250.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.new_game),
                            style = TextStyle(blacking)
                        )
                    }
                }
            }
            Box(
                modifier = modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(), contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = { navController.navigate("settingsScreen") },
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(),
                    modifier = modifier.width(250.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(Brush.horizontalGradient(colors = listOf(Pink80, Cyan)))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .width(250.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.settings),
                            style = TextStyle(blacking)
                        )
                    }
                }
            }
            Box(
                modifier = modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(), contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = { navController.navigate("recordScreen") },
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(),
                    modifier = modifier.width(250.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(Brush.horizontalGradient(colors = listOf(Pink80, Cyan)))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .width(250.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.records),
                            style = TextStyle(blacking)
                        )
                    }
                }
            }
            Box(
                modifier = modifier
                    .padding(top = 30.dp, bottom = 40.dp)
                    .fillMaxSize(), contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = { exitProcess(-1) },
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(),
                    modifier = modifier.width(250.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(Brush.horizontalGradient(colors = listOf(Pink80, Cyan)))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .width(250.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.quit_the_game),
                            style = TextStyle(blacking)
                        )
                    }
                }
            }
        }
    }
}